package com.serezka.lab.core.v2.command;

import com.serezka.lab.core.v2.api.objects.Request;
import com.serezka.lab.core.v2.api.objects.Response;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public abstract class Command {
    String name;
    String helpCode;
    int elementsRequired;

    public Command(String name, String helpCode) {
        this.name = name;
        this.helpCode = helpCode;
        this.elementsRequired = 0;
    }

    public abstract Response execute(Request request);
}
