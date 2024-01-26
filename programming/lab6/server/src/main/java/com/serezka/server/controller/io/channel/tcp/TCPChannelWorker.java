package com.serezka.server.controller.io.channel.tcp;

import com.serezka.server.controller.handler.Payload;
import com.serezka.server.controller.handler.Response;
import com.serezka.server.controller.io.channel.ChannelWorker;
import com.serezka.server.controller.seralizator.JsonSerializer;
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
    ServerSocket serverSocket;
    JsonSerializer serializer;

    public TCPChannelWorker(@Qualifier("serverSocket") ServerSocket serverSocket) {
        log.info("initializing TCPChannelWorker");
        this.serverSocket = serverSocket;
        this.serializer = new JsonSerializer();
    }


    @Override
    public void run() {
        for (; ; ) {
            try {
                log.info("waiting to client...");
                process();
                log.info("client disconnected");
            } catch (Exception e) {
                log.warn(e.getMessage());
            }
        }
    }

    private void process() throws Exception {
        Socket clientSocket = acceptClient();
        log.info("client successfully accepted!");

        if (clientSocket == null) {
            log.warn("client socket can't be null!");
            return;
        }

        try (BufferedReader clientReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             BufferedWriter clientWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {

            for (;;) {

                log.info("created reader and writer to channel");

                Payload clientPayload = serializer.deserialize(clientSocket.getInputStream());

            }
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

    @Override
    public void send(Response response) {

    }
}
