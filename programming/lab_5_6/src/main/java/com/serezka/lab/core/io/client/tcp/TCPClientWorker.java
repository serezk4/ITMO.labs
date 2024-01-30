package com.serezka.lab.core.io.client.tcp;

import com.serezka.lab.core.handler.Payload;
import com.serezka.lab.core.handler.Response;
import com.serezka.lab.core.io.client.ClientWorker;
import com.serezka.lab.core.serializer.SerializerDeserializer;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Log4j2
public class TCPClientWorker extends ClientWorker {
    // client socket to communicate with server
    @NonFinal
    Socket clientSocket;

    // serializer & deserializer for payload & response
    SerializerDeserializer<Payload> payloadSerializerDeserializer;
    SerializerDeserializer<Response> reponseSerializerDeserializer;

    // connection properties
    @Getter
    String address;
    @Getter
    int port;
    int maxReconnections;
    @NonFinal
    int currentReconnections = 0;

    public TCPClientWorker(@Qualifier("jsonPayloadSerializerDeserializer") SerializerDeserializer<Payload> payloadSerializerDeserializer,
                           @Qualifier("jsonResponseSerializerDeserializer") SerializerDeserializer<Response> responseSerializerDeserializer,
                           @Value("${server.address}") String address, @Value("${server.port}") int port,
                           @Value("${server.reconnections.max}") int maxReconnections) {
        log.info("initializing TCPChannelWorker");

        this.address = address;
        this.port = port;
        this.maxReconnections = maxReconnections;

        this.payloadSerializerDeserializer = payloadSerializerDeserializer;
        this.reponseSerializerDeserializer = responseSerializerDeserializer;
    }

    @Override
    public boolean isConnected() {
        if (clientSocket == null || !clientSocket.isConnected()) {
            connect();
            return clientSocket != null;
        }
        return true;
    }

    @Override
    public void connect() {
        try {
            log.info("trying to connect...");
            clientSocket = new Socket();
            clientSocket.connect(new InetSocketAddress(address, port), 2000);
            log.info("successfully connected to server {}:{}", address, port);
            send(Payload.connected());
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
            Thread.sleep(5000);
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
            clientSocket.getOutputStream().write(payloadSerializerDeserializer.serializeToByteArray(payload));
            clientSocket.getOutputStream().flush();

//            payloadSerializerDeserializer.serialize(payload, clientSocket.getOutputStream());
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
            return new Response();
        }
    }
}
