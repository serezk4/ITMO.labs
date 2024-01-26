package com.serezka.client;

import com.google.gson.Gson;
import org.springframework.core.serializer.Serializer;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

@Component
public class JsonSerializerDeserializer<T> implements Serializer<T> {
    private final Gson gson = new Gson();

//    @Override
//    public Update deserialize(InputStream inputStream) {
//        try (BufferedReader din = new BufferedReader(new InputStreamReader(inputStream))) {
//            System.out.println(din.readLine());
////            return gson.fromJson(din.readLine(), Update.class);
////            return new Update(new Product().generate(), "213");
//            return null;
//        } catch (Exception ex) {
//            return new Update(new Product().generate(), "123");
//        }
//    }

    @Override
    public void serialize(T update, OutputStream outputStream) {
        try (BufferedWriter dos = new BufferedWriter(new OutputStreamWriter(outputStream))) {
            dos.write(gson.toJson(update, Update.class));
            dos.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
