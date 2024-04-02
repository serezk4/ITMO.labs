package com.serezka.net.authorization;

import lombok.Getter;

@Getter
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
