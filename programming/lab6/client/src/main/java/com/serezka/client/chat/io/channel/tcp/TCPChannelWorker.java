package com.serezka.client.chat.io.channel.tcp;

import com.serezka.client.chat.handler.Payload;
import com.serezka.client.chat.handler.Response;
import com.serezka.client.chat.io.channel.ChannelWorker;
import com.serezka.client.chat.serializer.SerializerDeserializer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.*;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Log4j2
public class TCPChannelWorker extends ChannelWorker {
    // client socket to communicate with server
    @NonFinal
    Socket clientSocket;

    // serializer & deserializer for payload & response
    SerializerDeserializer<Payload> payloadSerializerDeserializer;
    SerializerDeserializer<Response> reponseSerializerDeserializer;

    // connection properties
    @Getter String address;
    @Getter int port;
    int maxReconnections;
    @NonFinal
    int currentReconnections = 0;

    public TCPChannelWorker(@Qualifier("jsonPayloadSerializerDeserializer") SerializerDeserializer<Payload> payloadSerializerDeserializer,
                            @Qualifier("jsonResponseSerializerDeserializer") SerializerDeserializer<Response> responseSerializerDeserializer,
                            @Value("${server.address}") String address, @Value("${server.port}") int port,
                            @Value("${server.reconnections.max}") int maxReconnections) {
        log.info("initializing TCPChannelWorker");

        this.address = address;
        this.port = port;
        this.maxReconnections = maxReconnections;

        log.info("trying connect to {}:{}", address, port);
        connect();
        log.info("successfully connected to server!");

        this.payloadSerializerDeserializer = payloadSerializerDeserializer;
        this.reponseSerializerDeserializer = responseSerializerDeserializer;
    }

    @Override
    public void connect() {
        try {
            log.info("trying to connect...");
            clientSocket = new Socket();
            clientSocket.connect(new InetSocketAddress(address, port), 2000);
            log.info("successfully connected to server {}:{}", address, port);
        } catch (ConnectException e) {
            log.warn("Error while connecting: {}", e.getMessage());
            reconnect();
        } catch (SocketTimeoutException e) {
            log.warn("Connection: {}.", e.getMessage());
            reconnect();
        } catch (IOException e) {
            log.warn("can't connect: {}", e.getMessage());
        }
    }

    @Override
    public void disconnect() {
        clientSocket = null;
    }

    @Override
    public void reconnect() {
        disconnect();

        log.info("[{}/{}] Trying to reconnect...", currentReconnections, maxReconnections);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.warn(e.getMessage());
        }

        if (currentReconnections < maxReconnections) {
            currentReconnections++;
            connect();
            return;
        }

        log.info("reconnection failed, program will be executed");
        disconnect();
        System.exit(1337);
    }

    @Override
    public void send(Payload payload) {
        try {
            payloadSerializerDeserializer.serialize(payload, clientSocket.getOutputStream());
        } catch (IOException e) {
            log.warn(e.getMessage());
        }
    }

    @Override
    public Response get() {
        try {
            return reponseSerializerDeserializer.deserialize(clientSocket.getInputStream());
        } catch (IOException e) {
            log.warn(e.getMessage());
            return Response.empty();
        }
    }
}
