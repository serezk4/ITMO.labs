package com.serezka.net.methods.authorization;

import com.google.gson.Gson;
import com.serezka.net.Method;
import com.serezka.net.RestClient;
import okhttp3.Response;

import java.io.IOException;

public class Login extends Method<AuthenticationResponse> {
    Gson gson = new Gson();

    String username;
    String password;

    public Login(String username, String password) {
        super("/login");

        this.username = username;
        this.password = password;
    }

    @Override
    public String getUrl() {
        return String.format("%s?username=%s&password=%s",getBaseUrl(), username, password);
    }

    @Override
    public AuthenticationResponse mapResponse(Response response) throws IOException {
        if (response.body() == null) return new AuthenticationResponse(true, "error empty response");
        return gson.fromJson(response.body().string(), AuthenticationResponse.class);
    }

    @Override
    public AuthenticationResponse execute() throws IOException {
        return mapResponse(RestClient.getInstance().post(this));
    }

}
