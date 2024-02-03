package com.serezka.lab.core.io.server.tcp;

import com.serezka.lab.core.handler.Payload;
import com.serezka.lab.core.handler.Response;
import com.serezka.lab.core.io.server.ServerWorker;
import com.serezka.lab.core.io.serializer.SerializerDeserializer;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Log4j2
public class TCPServerWorker implements ServerWorker {
    ServerSocket serverSocket;
    SerializerDeserializer<Payload> payloadSerializerDeserializer;
    SerializerDeserializer<Response> reponseSerializerDeserializer;

    @NonFinal
    Socket clientSocket = null;

    public TCPServerWorker(@Qualifier("serverSocket") ServerSocket serverSocket,
                           @Qualifier("jsonPayloadSerializerDeserializer") SerializerDeserializer<Payload> payloadSerializerDeserializer,
                           @Qualifier("jsonResponseSerializerDeserializer") SerializerDeserializer<Response> responseSerializerDeserializer) {
        log.info("initializing TCPChannelWorker");

        this.serverSocket = serverSocket;

        this.payloadSerializerDeserializer = payloadSerializerDeserializer;
        this.reponseSerializerDeserializer = responseSerializerDeserializer;
    }

    @Override
    public void send(Response response) {
        try {
            reponseSerializerDeserializer.serialize(response, clientSocket.getOutputStream());
        } catch (IOException e) {
            log.warn(e.getMessage());
            this.clientSocket = null;
        }
    }

    @Override
    public Payload get() {
        try {
            return payloadSerializerDeserializer.deserialize(clientSocket.getInputStream());
        } catch (IOException e) {
            log.warn(e.getMessage());
            this.clientSocket = null;
            return Payload.empty();
        }
    }

    @Override
    public void acceptClient() {
        try {
            log.info("accepting client...");

            serverSocket.setSoTimeout(5000);
            clientSocket = serverSocket.accept();

            if (clientSocket == null) log.warn("client socket can't be null!");

            log.info("client {} accepted", clientSocket.toString());
        } catch (IOException e) {
            log.warn(e.getMessage());
        }
    }

    @Override
    public boolean isConnected() {
        return !(clientSocket == null || clientSocket.isClosed() );
    }
}
