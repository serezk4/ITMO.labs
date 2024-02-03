package com.serezka.lab.core.io.socket.objects.encoder;

import com.google.gson.Gson;
import com.serezka.lab.core.io.socket.objects.Payload;
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
public class JsonPayloadEncoder extends MessageToMessageEncoder<Payload> {
    Gson gson;
    Charset charset;

    public JsonPayloadEncoder(Gson gson, @Value("${server.charset}") String charset) {
        this.gson = gson;
        this.charset = Charset.forName(charset);
    }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Payload payload, List<Object> list) throws Exception {
        list.add(gson.toJson(payload, Payload.class).getBytes(charset));
    }
}
