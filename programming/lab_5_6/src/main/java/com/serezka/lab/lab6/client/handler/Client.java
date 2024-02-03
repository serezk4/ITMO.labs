package com.serezka.lab.lab6.client.handler;

import com.serezka.lab.core.io.client.tcp.TCPClientWorker;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:client.properties")
public class Client {
    int port;
    Channel channel;
    EventLoopGroup workGroup = new NioEventLoopGroup();

    TCPClientWorker clientWorker;

    public Client(@Value("${server.port}") int port, TCPClientWorker clientWorker) {
        this.port = port;

        this.clientWorker = clientWorker;
    }

    public ChannelFuture startup() throws Exception {
        try {
            Bootstrap b = new Bootstrap();
            b.group(workGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(clientWorker);
                }
            });
            ChannelFuture channelFuture = b.connect("127.0.0.1", this.port).sync();
            this.channel = channelFuture.channel();

            return channelFuture;
        } finally {
        }
    }

    public void shutdown() {
        workGroup.shutdownGracefully();
    }
}
