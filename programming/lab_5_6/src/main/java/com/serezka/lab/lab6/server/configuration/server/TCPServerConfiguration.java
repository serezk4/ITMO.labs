package com.serezka.lab.lab6.server.configuration.server;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

@Configuration
@PropertySource("classpath:server.properties")
@Log4j2(topic = "ServerConfiguration")
public class TCPServerConfiguration {
    @Bean(name = "serverSocket")
    public ServerSocket serverSocket(@Value("${server.port}") int port,
                                     @Value("${server.connections.max}") int maxConnections) throws IOException {

        ServerSocket serverSocket = new ServerSocket(port, maxConnections);
        serverSocket.setSoTimeout(10000);

        log.info("initialized server {}:{} with max connections {}", "localhost", port, maxConnections);

        return serverSocket;
    }
}
