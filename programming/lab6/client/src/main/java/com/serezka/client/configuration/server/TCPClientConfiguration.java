package com.serezka.client.configuration.server;


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
        Socket clientSocket = new Socket(address, port);
        log.info("successfully connected to server!");
        return clientSocket;
    }

    @Bean(name = "serverReader")
    public InputStream serverInputStream(@Qualifier("clientSocket") Socket socket) throws IOException {
        return socket.getInputStream();
    }

    @Bean(name = "serverWriter")
    public OutputStream serverOutputStream(@Qualifier("clientSocket") Socket socket) throws IOException {
        return socket.getOutputStream();
    }
}
