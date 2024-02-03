package com.serezka.lab.core.io.socket.client.tcp;

import com.serezka.lab.core.io.socket.objects.Payload;
import com.serezka.lab.core.io.socket.objects.Response;
import com.serezka.lab.core.io.socket.client.ClientWorker;
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
public class TCPClientWorker implements ClientWorker {
    // handler
    SimpleChannelInboundHandler<Response> handler;

    // client socket to communicate with server
    int port;
    String address;

    // serializer & deserializer for payload & response
    MessageToMessageEncoder<Payload> payloadEncoder;
    MessageToMessageDecoder<ByteBuf> responseDecoder;

    public TCPClientWorker(@Qualifier("jsonPayloadEncoder") MessageToMessageEncoder<Payload> payloadEncoder,
                           @Qualifier("jsonResponseDecoder") MessageToMessageDecoder<ByteBuf> responseDecoder,
                           @Value("${server.address}") String address, @Value("${server.port}") int port,
                           @Qualifier("client") SimpleChannelInboundHandler<Response> handler) {
        this.address = address;
        this.port = port;

        this.payloadEncoder = payloadEncoder;
        this.responseDecoder = responseDecoder;

        this.handler = handler;
    }

    @SneakyThrows
    @Override
    public void run() {

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

//                .option(ChannelOption.AUTO_CLOSE, true)
//                .option(ChannelOption.SO_REUSEADDR, true)
                .option(ChannelOption.SO_KEEPALIVE, true)
//                .option(ChannelOption.TCP_NODELAY, true)

                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(@NonNull SocketChannel channel) {
                        channel.pipeline().addLast("decoder", responseDecoder);
                        channel.pipeline().addLast("encoder", payloadEncoder);
                        channel.pipeline().addLast(handler);
                    }
                });

        channelFuture = bootstrap.connect(address, port).sync();
//        channelFuture.channel().closeFuture().sync();

//        channelFuture.channel().writeAndFlush(Payload.connected()).addListener((ChannelFutureListener) channelFuture -> System.out.println(channelFuture.get()));

        log.info("successfully initialized client!");
    }

    @Override
    public void close() throws Exception {
        workGroup.close();
        channelFuture.channel().close();
    }
}