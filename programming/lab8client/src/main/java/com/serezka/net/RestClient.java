package com.serezka.net;

import com.serezka.configuration.Configuration;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class RestClient {
    static RestClient instance = null;

    final OkHttpClient client;

    private RestClient() {
        client = new OkHttpClient();
    }

    public static RestClient getInstance() {
        if (instance == null)
            instance = new RestClient();
        return instance;
    }

    private String getMappedUrl(String rawUrl) {
        return Configuration.SERVER_URL + rawUrl;
    }

    public Response post(Method method) throws IOException {
        return client.newCall(
                new Request.Builder()
                        .url(getMappedUrl(method.getUrl()))
                        .post(RequestBody.create("", null))
                        .build()
        ).execute();
    }

    public Response get(Method method) throws IOException {
        return client.newCall(
                new Request.Builder()
                        .url(getMappedUrl(method.getUrl()))
                        .get()
                        .build()
        ).execute();
    }

    public Response get(Method method, String token) throws IOException {
        return client.newCall(
                new Request.Builder()
                        .url(getMappedUrl(method.getUrl()))
                        .addHeader("Authorization", "Bearer " + token)
                        .get()
                        .build()
        ).execute();
    }
}
