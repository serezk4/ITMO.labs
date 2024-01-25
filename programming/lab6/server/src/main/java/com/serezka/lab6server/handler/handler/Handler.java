package com.serezka.lab6server.handler.handler;

import com.serezka.lab6server.handler.user.Data;
import lombok.Getter;

@Getter
public class Handler {
    public Data data = Data.getInstance();
}
