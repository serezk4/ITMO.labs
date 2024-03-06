package com.serezka.lab.core.v1.io.socket.objects.encoder;

import com.google.gson.Gson;
import com.serezka.lab.core.v1.io.socket.objects.Payload;
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
@Component("jsonPayloadEncoder")
@PropertySource("classpath:server.properties")
@ChannelHandler.Sharable
public class JsonPayloadEncoder extends MessageToMessageEncoder<Payload> {
    Gson gson;
    Charset charset;

    public JsonPayloadEncoder(Gson gson, @Value("${server.charset}") String charset) {
        this.gson = gson;
        this.charset = Charset.forName(charset);
    }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Payload payload, List<Object> list) throws Exception {
        System.out.println(gson.toJson(payload, Payload.class));
        byte[] bytes = gson.toJson(payload, Payload.class).getBytes(charset);
        ByteBuf buffer = Unpooled.wrappedBuffer(bytes);
        list.add(buffer);
    }
}
