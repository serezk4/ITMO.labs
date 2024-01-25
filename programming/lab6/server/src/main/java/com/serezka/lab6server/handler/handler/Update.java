package com.serezka.lab6server.handler.handler;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Getter @RequiredArgsConstructor
public class Update {
    String message;
}
