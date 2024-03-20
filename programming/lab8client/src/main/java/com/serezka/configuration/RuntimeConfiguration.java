package com.serezka.configuration;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RuntimeConfiguration {
    private static volatile String JWT_TOKEN;

    public static String getJwtToken() {
        return JWT_TOKEN;
    }

    public static void setJwtToken(String jwtToken) {
        JWT_TOKEN = jwtToken;
    }
}
