package com.serezka.server.controller.serializer;

import com.google.gson.Gson;
import com.serezka.server.controller.handler.Payload;
import com.serezka.server.controller.handler.Response;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import reactor.util.annotation.NonNull;

import java.io.*;

@Component
@Log4j2
public class JsonPayloadSerializerDeserializer implements SerializerDeserializer<Payload> {
    private final Gson gson = new Gson();

    @Override
    @NonNull
    public Payload deserialize(@NonNull InputStream inputStream) throws IOException {
        BufferedReader din = new BufferedReader(new InputStreamReader(inputStream));
        return gson.fromJson(din.readLine(), Payload.class);
    }

    @Override
    public void serialize(@NonNull Payload payload, @NonNull OutputStream outputStream) throws IOException {
        BufferedWriter dos = new BufferedWriter(new OutputStreamWriter(outputStream));
        dos.write(gson.toJson(payload, Payload.class));
        dos.flush();
    }
}
