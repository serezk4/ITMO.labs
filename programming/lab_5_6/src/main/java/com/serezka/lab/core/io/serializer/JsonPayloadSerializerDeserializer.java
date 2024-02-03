package com.serezka.lab.core.io.serializer;

import com.google.gson.Gson;
import com.serezka.lab.core.handler.Payload;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;

@Component
@Log4j2
public class JsonPayloadSerializerDeserializer implements SerializerDeserializer<Payload> {
    private final Gson gson = new Gson();

    @Override
    @NonNull
    public Payload deserialize(@NonNull InputStream inputStream) throws IOException {
        return gson.fromJson(new String(inputStream.readAllBytes(), StandardCharsets.UTF_8), Payload.class);
    }

    @Override
    public void serialize(@NonNull Payload payload, @NonNull OutputStream outputStream) throws IOException {
        outputStream.write(gson.toJson(payload, Payload.class).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
    }
}
