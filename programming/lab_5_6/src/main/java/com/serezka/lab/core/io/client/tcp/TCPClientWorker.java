package com.serezka.lab.core.io.client.tcp;

import com.serezka.lab.core.handler.Handler;
import com.serezka.lab.core.handler.Payload;
import com.serezka.lab.core.handler.Response;
import com.serezka.lab.core.io.client.ClientWorker;
import com.serezka.lab.lab6.client.handler.Client;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.MessageToMessageEncoder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.Socket;
import java.nio.charset.Charset;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Log4j2
public class TCPClientWorker implements ClientWorker {
    Client client;

    // client socket to communicate with server
    int port;
    @Getter String address;
    int maxReconnections;
    @NonFinal int currentReconnections = 0;

    // serializer & deserializer for payload & response
    MessageToMessageEncoder<Payload> payloadEncoder;
    MessageToMessageDecoder<ByteBuf> responseDecoder;

    // connection properties

    public TCPClientWorker(@Qualifier("jsonPayloadEncoder") MessageToMessageEncoder<Payload> payloadEncoder,
                           @Qualifier("jsonResponseDecoder") MessageToMessageDecoder<ByteBuf> responseDecoder,
                           @Value("${server.address}") String address, @Value("${server.port}") int port,
                           @Value("${server.reconnections.max}") int maxReconnections, Client client) {
        log.info("initializing TCPChannelWorker");

        this.address = address;
        this.port = port;
        this.maxReconnections = maxReconnections;

        this.payloadEncoder = payloadEncoder;
        this.responseDecoder = responseDecoder;

        this.client = client;
    }

    @SneakyThrows
    @Override
    public void run() {
        init();
    }

    @NonFinal EventLoopGroup workGroup;
    @NonFinal Bootstrap bootstrap;

    @NonFinal ChannelFuture channelFuture;

    @SneakyThrows
    @Override
    public void init() {
        bootstrap = new Bootstrap();
        workGroup = new NioEventLoopGroup();

        bootstrap.group(workGroup)
                .channel(NioSocketChannel.class)

                .option(ChannelOption.AUTO_CLOSE, true)
                .option(ChannelOption.SO_REUSEADDR, true)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)

                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(@NonNull SocketChannel channel) {
                        channel.pipeline().addLast("decoder", responseDecoder);
                        channel.pipeline().addLast("encoder", payloadEncoder);
                        channel.pipeline().addLast(client);
                    }
                });

        channelFuture = bootstrap.connect(address, port).sync();
    }

    @Override
    public void close() throws Exception {
        workGroup.close();
        channelFuture.channel().close();
    }
}
