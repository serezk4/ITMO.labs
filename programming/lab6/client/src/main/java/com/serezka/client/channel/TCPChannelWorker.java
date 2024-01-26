package com.serezka.client.channel;

import com.serezka.client.JsonSerializerDeserializer;
import com.serezka.client.Update;
import com.serezka.client.object.Product;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.Socket;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Log4j2
@RequiredArgsConstructor
public class TCPChannelWorker implements ChannelWorker {
    Socket clientSocket;

    BufferedReader serverReader;
    BufferedWriter serverWriter;

    JsonSerializerDeserializer<Update> serializer;

    @Override
    public void send(String text) {
        send(new Update());
    }

    @Override
    public void send(Update update) {

    }

    @Override
    public void run() {
        while (true) {
            try {
                serializer.serialize(new Update(new Product().generate(),"123"), clientSocket.getOutputStream());
            } catch (IOException e) {
                log.warn(e.getMessage());
                return;
            }
        }
    }
}
