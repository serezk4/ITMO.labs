package com.serezka.lab.core.io.client.tcp;

import com.serezka.lab.core.handler.Payload;
import com.serezka.lab.core.handler.Response;
import com.serezka.lab.core.io.client.ClientWorker;
import com.serezka.lab.core.io.serializer.SerializerDeserializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.MessageToMessageEncoder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Log4j2
public class TCPClientWorker extends ClientWorker {
    // client socket to communicate with server
    @NonFinal
    Socket clientSocket;

    // serializer & deserializer for payload & response
    MessageToMessageEncoder<Payload> payloadEncoder;
    MessageToMessageDecoder<ByteBuf> responseDecoder;

    // connection properties
    @Getter String address;
    @Getter int port;
    int maxReconnections;
    @NonFinal int currentReconnections = 0;

    public TCPClientWorker(@Qualifier("jsonPayloadEncoder") MessageToMessageEncoder<Payload> payloadEncoder,
                           @Qualifier("jsonResponseDecoder") MessageToMessageDecoder<ByteBuf> responseDecoder,
                           @Value("${server.address}") String address, @Value("${server.port}") int port,
                           @Value("${server.reconnections.max}") int maxReconnections) {
        log.info("initializing TCPChannelWorker");

        this.address = address;
        this.port = port;
        this.maxReconnections = maxReconnections;

        this.payloadEncoder = payloadEncoder;
        this.responseDecoder = responseDecoder;
    }

    @Override
    public boolean isConnected() {
        return !(clientSocket == null || clientSocket.isClosed());
    }

    @Override
    public void connect() {

    }

    @Override
    public void disconnect() {
        clientSocket = null;
    }

    @Override
    public void reconnect() {

    }

    @Override
    public void send(Payload payload) {

    }

    @Override
    public Response get() {
        return null;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        String message = byteBuf.toString(Charset.defaultCharset());
        System.out.println("Received Message : " + message);
    }

    @Override
    public boolean acceptInboundMessage(Object msg) throws Exception {
        return super.acceptInboundMessage(msg);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
    }
}
