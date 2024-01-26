package com.serezka.client.configuration.client;


import com.serezka.client.JsonSerializerDeserializer;
import com.serezka.client.Update;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.*;
import java.net.Socket;

@Configuration
@PropertySource("classpath:client.properties")
@Log4j2
public class TCPClientConfiguration {
    @Bean(name = "clientSocket")
    public Socket clientSocket(@Value("${server.port}") int port,
                               @Value("${server.address}") String address) throws IOException {
        log.info("trying connect to {}:{}", address, port);
        return new Socket(address, port);
    }

    @Bean(name = "serverReader")
    public BufferedReader serverReader(@Qualifier("clientSocket") Socket socket) throws IOException {
        return new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Bean(name = "serverWriter")
    public BufferedWriter serverWriter(@Qualifier("clientSocket") Socket socket) throws IOException {
        return new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    @Bean
    public JsonSerializerDeserializer<Update> jsonSerializerDeserializer() {
        return new JsonSerializerDeserializer<Update>();
    }
}
