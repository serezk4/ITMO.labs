package com.serezka.lab.core.io.socket.objects.decoder;

import com.google.gson.Gson;
import com.serezka.lab.core.io.socket.objects.Response;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component("jsonResponseDecoder")
@PropertySource("classpath:server.properties")
public class JsonResponseDecoder extends MessageToMessageDecoder<ByteBuf> {
    Gson gson;
    Charset charset;

    public JsonResponseDecoder(Gson gson, @Value("${server.charset}") String charset) {
        this.gson = gson;
        this.charset = Charset.forName(charset);
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        list.add(gson.fromJson(byteBuf.toString(charset), Response.class));
    }
}