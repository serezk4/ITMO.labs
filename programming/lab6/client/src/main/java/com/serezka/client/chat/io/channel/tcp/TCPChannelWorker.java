package com.serezka.client.chat.io.channel.tcp;

import com.serezka.client.chat.handler.Payload;
import com.serezka.client.chat.handler.Response;
import com.serezka.client.chat.io.channel.ChannelWorker;
import com.serezka.client.chat.serializer.SerializerDeserializer;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
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
    Socket clientSocket;

    SerializerDeserializer<Payload> payloadSerializerDeserializer;
    SerializerDeserializer<Response> reponseSerializerDeserializer;

    public TCPChannelWorker(@Qualifier("clientSocket") Socket clientSocket,
                            @Qualifier("jsonPayloadSerializerDeserializer") SerializerDeserializer<Payload> payloadSerializerDeserializer,
                            @Qualifier("jsonResponseSerializerDeserializer") SerializerDeserializer<Response> responseSerializerDeserializer) {
        log.info("initializing TCPChannelWorker");

        this.clientSocket = clientSocket;

        this.payloadSerializerDeserializer = payloadSerializerDeserializer;
        this.reponseSerializerDeserializer = responseSerializerDeserializer;
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
