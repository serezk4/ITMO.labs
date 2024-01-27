package com.serezka.server.controller.io.channel.tcp;

import com.serezka.server.controller.handler.Payload;
import com.serezka.server.controller.handler.Response;
import com.serezka.server.controller.io.channel.ChannelWorker;
import com.serezka.server.controller.serializer.SerializerDeserializer;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Log4j2
public class TCPChannelWorker implements ChannelWorker {
    ServerSocket serverSocket;
    SerializerDeserializer<Payload> payloadSerializerDeserializer;
    SerializerDeserializer<Response> reponseSerializerDeserializer;

    @NonFinal
    Socket clientSocket = null;

    public TCPChannelWorker(@Qualifier("serverSocket") ServerSocket serverSocket,
                            @Qualifier("jsonPayloadSerializerDeserializer") SerializerDeserializer<Payload> payloadSerializerDeserializer,
                            @Qualifier("jsonResponseSerializerDeserializer") SerializerDeserializer<Response> responseSerializerDeserializer) {
        log.info("initializing TCPChannelWorker");

        this.serverSocket = serverSocket;

        this.payloadSerializerDeserializer = payloadSerializerDeserializer;
        this.reponseSerializerDeserializer = responseSerializerDeserializer;
    }

    @Override
    public void send(Response response) {
        if (clientSocket == null) clientSocket = acceptClient();

        try {
            reponseSerializerDeserializer.serialize(response, clientSocket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Payload get() {
        if (clientSocket == null) clientSocket = acceptClient();

        try {
            return payloadSerializerDeserializer.deserialize(clientSocket.getInputStream());
        } catch (IOException e) {
            log.warn(e.getMessage());
            return Payload.empty();
        }
    }

    @Override
    public Socket acceptClient() {
        try {
            return serverSocket.accept();
        } catch (IOException e) {
            log.warn(e.getMessage());
            return null;
        }
    }
}
