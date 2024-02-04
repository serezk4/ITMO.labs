package com.serezka.lab.core.io.socket.objects;

import com.serezka.lab.core.database.model.Flat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data @AllArgsConstructor @NoArgsConstructor
@Builder
public class Payload implements Serializable {
    State state = null;
    String command = null;

    Flat flat = null;
    String string = null;

    public Payload(String command, Flat flat, String string) {
        this.command = command;
        this.flat = flat;
        this.string = string;
    }

    public static Payload empty() {
        return new Payload();
    }

    public static Payload connected() {
        return new Payload(State.CONNECTED,null, null, null);
    }
}