package com.serezka.lab.core.io.socket.server.tcp;

import com.serezka.lab.core.io.socket.objects.Payload;
import com.serezka.lab.core.io.socket.objects.Response;
import com.serezka.lab.core.io.socket.server.ServerWorker;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
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
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Log4j2
@PropertySource("classpath:server.properties")
public class TCPServerWorker implements ServerWorker {
    MessageToMessageEncoder<Response> responseEncoder;
    MessageToMessageDecoder<ByteBuf> payloadDecoder;

    SimpleChannelInboundHandler<Payload> handler;

    // server properties
    int port;

    public TCPServerWorker(@Value("${lab6.server.port}") int port,
                           @Qualifier("jsonResponseEncoder") MessageToMessageEncoder<Response> responseEncoder,
                           @Qualifier("jsonPayloadDecoder") MessageToMessageDecoder<ByteBuf> payloadDecoder,
                           @Qualifier("lab6serverHandler") SimpleChannelInboundHandler<Payload> handler) {
        log.info("initializing TCPChannelWorker on {}:{}", "localhost", port);

        this.port = port;

        this.responseEncoder = responseEncoder;
        this.payloadDecoder = payloadDecoder;

        this.handler = handler;
    }

    @NonFinal EventLoopGroup bossGroup;
    @NonFinal EventLoopGroup workerGroup;

    @NonFinal ServerBootstrap bootstrap;
    @NonFinal ChannelFuture channelFuture;

    @SneakyThrows
    @Override
    public void init() {
        log.info("initializing server...");

        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();
        bootstrap = new ServerBootstrap();

        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)

                .option(ChannelOption.SO_BACKLOG, 1024)
                .option(ChannelOption.AUTO_CLOSE, true)
                .option(ChannelOption.SO_REUSEADDR, true)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)

                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(@NonNull SocketChannel channel) {
                        channel.pipeline().addLast("decoder", payloadDecoder);
                        channel.pipeline().addLast("encoder", responseEncoder);
                        channel.pipeline().addLast(handler);
                    }
                });

        channelFuture = bootstrap.bind(port).sync();
//        channelFuture.channel().closeFuture().sync();


        log.info("successfully initialized server!");
    }

    @Override
    public void close() {
        workerGroup.shutdownGracefully();
    }
}
