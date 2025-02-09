package com.serezka.server.controller.serializer;

import com.google.gson.Gson;
import com.serezka.server.controller.handler.Response;
import org.springframework.stereotype.Component;
import reactor.util.annotation.NonNull;

import java.io.*;

@Component
public class JsonResponseSerializerDeserializer implements SerializerDeserializer<Response> {
    private final Gson gson = new Gson();

    @Override
    @NonNull
    public Response deserialize(@NonNull InputStream inputStream) throws IOException {
        BufferedReader din = new BufferedReader(new InputStreamReader(inputStream));
        return gson.fromJson(din.readLine(), Response.class);

    }

    @Override
    public void serialize(@NonNull Response payload, @NonNull OutputStream outputStream) throws IOException {
        BufferedWriter dos = new BufferedWriter(new OutputStreamWriter(outputStream));
        dos.write(gson.toJson(payload, Response.class));
        dos.flush();
    }
}