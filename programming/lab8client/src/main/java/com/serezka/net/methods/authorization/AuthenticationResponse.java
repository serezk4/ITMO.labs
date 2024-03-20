package com.serezka.net.methods.authorization;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AuthenticationResponse {
    private boolean error = false;
    private String token;

    public AuthenticationResponse(String token) {
        this.token = token;
    }

    public AuthenticationResponse(boolean error, String token) {
        this.error = error;
        this.token = token;
    }
}