package com.serezka.lab5.chat.hahdler;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter @AllArgsConstructor
public class Update {
    String message;
}
