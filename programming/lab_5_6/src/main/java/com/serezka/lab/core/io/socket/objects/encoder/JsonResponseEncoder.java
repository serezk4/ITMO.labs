package com.serezka.lab.core.io.socket.objects.encoder;

import com.google.gson.Gson;
import com.serezka.lab.core.io.socket.objects.Response;
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
public class JsonResponseEncoder extends MessageToMessageEncoder<Response> {
    Gson gson;
    Charset charset;

    public JsonResponseEncoder(Gson gson, @Value("${server.charset}") String charset) {
        this.gson = gson;
        this.charset = Charset.forName(charset);
    }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Response response, List<Object> list) throws Exception {
        list.add(gson.toJson(response, Response.class).getBytes(charset));
    }
}
