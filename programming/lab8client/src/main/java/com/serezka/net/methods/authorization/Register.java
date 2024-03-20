package com.serezka.net.methods.authorization;

import com.google.gson.Gson;
import com.serezka.net.Method;
import com.serezka.net.RestClient;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import okhttp3.Response;

import java.io.IOException;

@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class Register extends Method<AuthenticationResponse> {
    Gson gson = new Gson();

    String mail;
    String username;
    String password;

    public Register(String mail, String username, String password) {
        super("/register");

        this.mail = mail;
        this.username = username;
        this.password = password;
    }

    @Override
    public String getUrl() {
        return String.format("%s?username=%s&password=%s&mail=%s",getBaseUrl(), username, password, mail);
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
