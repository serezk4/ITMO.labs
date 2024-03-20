package com.serezka.configuration;

public class Configuration {
    public static final String SERVER_HOST = "localhost";
    public static final int SERVER_PORT = 8080;
    public static final String SERVER_PROTOCOL = "http";

    public static final String SERVER_URL = SERVER_PROTOCOL + "://" + SERVER_HOST + ":" + SERVER_PORT;
}
