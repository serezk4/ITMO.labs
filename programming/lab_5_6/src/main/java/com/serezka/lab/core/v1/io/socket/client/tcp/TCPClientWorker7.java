package com.serezka.lab.core.v1.io.socket.client.tcp;

import com.serezka.lab.core.v1.io.socket.objects.Payload;
import com.serezka.lab.core.v1.io.socket.client.ClientWorker;
import com.serezka.lab.core.v1.io.socket.objects.Response;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.MessageToMessageEncoder;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Log4j2
public class TCPClientWorker7 implements ClientWorker {
    // handler
    SimpleChannelInboundHandler<com.serezka.lab.core.v1.io.socket.objects.Response> handler;

    // client socket to communicate with server
    int port;
    String address;

    // serializer & deserializer for payload & response
    MessageToMessageEncoder<Payload> payloadEncoder;
    MessageToMessageDecoder<ByteBuf> responseDecoder;

    public TCPClientWorker7(@Qualifier("jsonPayloadEncoder") MessageToMessageEncoder<Payload> payloadEncoder,
                            @Qualifier("jsonResponseDecoder") MessageToMessageDecoder<ByteBuf> responseDecoder,
                            @Value("${lab7.server.address}") String address, @Value("${lab7.server.port}") int port,
                            @Qualifier("lab7client") SimpleChannelInboundHandler<Response> handler) {
        this.address = address;
        this.port = port;

        this.payloadEncoder = payloadEncoder;
        this.responseDecoder = responseDecoder;

        this.handler = handler;
    }

    @NonFinal EventLoopGroup workGroup;
    @NonFinal Bootstrap bootstrap;

    @NonFinal ChannelFuture channelFuture;

    @SneakyThrows
    @Override
    public void init() {
        log.info("initializing client...");

        bootstrap = new Bootstrap();
        workGroup = new NioEventLoopGroup();

        bootstrap.group(workGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(@NonNull SocketChannel channel) {
                        channel.pipeline().addLast("decoder", responseDecoder);
                        channel.pipeline().addLast("encoder", payloadEncoder);
                        channel.pipeline().addLast(handler);
                    }
                });

        channelFuture = bootstrap.connect(address, port).sync();

        log.info("successfully initialized client!");
    }

    @Override
    public boolean isConnected() {
        return !(channelFuture == null || !channelFuture.channel().isOpen() || !channelFuture.channel().isActive());
    }

    @Override
    public String getInfo() {
        if (channelFuture == null) return "ожидание...";

        return String.format("%s -> %s",
                channelFuture.channel().localAddress().toString(),
                channelFuture.channel().remoteAddress().toString());
    }

    @Override
    public void close() {
        channelFuture.channel().close();
        workGroup.shutdownGracefully();
    }

    @Override
    public void disconnect() {
        close();
    }
}