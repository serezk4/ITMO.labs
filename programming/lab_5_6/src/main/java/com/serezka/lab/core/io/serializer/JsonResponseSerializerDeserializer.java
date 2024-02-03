package com.serezka.lab.core.io.serializer;

import com.google.gson.Gson;
import com.serezka.lab.core.handler.Response;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;

@Component
public class JsonResponseSerializerDeserializer implements SerializerDeserializer<Response> {
    private final Gson gson = new Gson();

    @Override @NonNull
    public Response deserialize(@NonNull InputStream inputStream) throws IOException {
        return gson.fromJson(new String(inputStream.readAllBytes(), StandardCharsets.UTF_8), Response.class);
    }

    @Override
    public void serialize(@NonNull Response response, @NonNull OutputStream outputStream) throws IOException {
        outputStream.write(gson.toJson(response, Response.class).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
    }
}