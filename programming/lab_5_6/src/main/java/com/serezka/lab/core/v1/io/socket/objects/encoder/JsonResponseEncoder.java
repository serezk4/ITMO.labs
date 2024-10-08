package com.serezka.lab.core.v1.io.socket.objects.encoder;

import com.google.gson.Gson;
import com.serezka.lab.core.v1.io.socket.objects.Response;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component("jsonResponseEncoder") @PropertySource("classpath:server.properties")
@ChannelHandler.Sharable
public class JsonResponseEncoder extends MessageToMessageEncoder<com.serezka.lab.core.v1.io.socket.objects.Response> {
    Gson gson;
    Charset charset;

    public JsonResponseEncoder(Gson gson, @Value("${server.charset}") String charset) {
        this.gson = gson;
        this.charset = Charset.forName(charset);
    }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, com.serezka.lab.core.v1.io.socket.objects.Response response, List<Object> list) throws Exception {
        byte[] bytes = gson.toJson(response, Response.class).getBytes(charset);
        ByteBuf buffer = Unpooled.wrappedBuffer(bytes);
        list.add(buffer);
    }
}