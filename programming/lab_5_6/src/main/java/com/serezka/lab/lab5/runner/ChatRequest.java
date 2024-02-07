package com.serezka.lab.lab5.runner;

import com.serezka.lab.core.database.model.Flat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Setter @Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChatRequest {
    String message;
    Set<Flat> temporaryCollection;
}