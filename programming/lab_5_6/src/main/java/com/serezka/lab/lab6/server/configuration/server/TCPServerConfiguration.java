package com.serezka.lab.lab6.server.configuration.server;

import com.serezka.lab.core.io.socket.objects.Payload;
import com.serezka.lab.core.io.socket.objects.Response;
import com.serezka.lab.core.io.socket.server.tcp.TCPServerWorker;
import io.netty.buffer.ByteBuf;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.MessageToMessageEncoder;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:server.properties")
@Log4j2(topic = "ServerConfiguration")
public class TCPServerConfiguration {
//    @Bean(name = "serverSocket")
//    public ServerSocket serverSocket(@Value("${server.port}") int port,
//                                     @Value("${server.connections.max}") int maxConnections) throws IOException {
//
//        ServerSocket serverSocket = new ServerSocket(port, maxConnections);
//        serverSocket.setSoTimeout(10000);
//
//        log.info("initialized server {}:{} with max connections {}", "localhost", port, maxConnections);
//
//        return serverSocket;
//    }

    @Bean(name = "lab6server")
    public TCPServerWorker tcpServerWorker(@Value("${server.port}") int port,
                                           @Qualifier("jsonResponseEncoder") MessageToMessageEncoder<Response> responseEncoder,
                                           @Qualifier("jsonPayloadDecoder") MessageToMessageDecoder<ByteBuf> payloadDecoder,
                                           @Qualifier("lab6serverHandler") SimpleChannelInboundHandler<Payload> handler) throws Exception {
        return new TCPServerWorker(port, responseEncoder, payloadDecoder, handler);
    }
}
