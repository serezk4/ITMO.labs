package com.serezka.server.server;

import com.google.gson.Gson;
import com.serezka.server.handler.handler.Update;
import com.serezka.server.handler.object.Product;
import org.springframework.core.serializer.Deserializer;
import org.springframework.core.serializer.Serializer;
import reactor.util.annotation.NonNull;

import java.io.*;
import java.util.Arrays;

public class JsonSerializerDeserializer implements Serializer<Update>, Deserializer<Update> {
    private final Gson gson = new Gson();

    @Override
    @NonNull
    public Update deserialize(@NonNull InputStream inputStream) throws IOException {

        try (BufferedReader din = new BufferedReader(new InputStreamReader(inputStream))) {
            return gson.fromJson(din.readLine(), Update.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void serialize(@NonNull Update update, @NonNull OutputStream outputStream) throws IOException {
        try (BufferedWriter dos = new BufferedWriter(new OutputStreamWriter(outputStream))) {
            dos.write(gson.toJson(update, Update.class));
            dos.flush();
        }
    }
}
