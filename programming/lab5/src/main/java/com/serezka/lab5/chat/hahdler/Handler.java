package com.serezka.lab5.chat.hahdler;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class Handler {

    public void handleQuery(String message) {
        // make update

        // get command

        // execute
    }
}
