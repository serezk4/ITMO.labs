package com.serezka.lab.core.io.socket.objects;

import com.serezka.lab.core.database.model.Flat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data @AllArgsConstructor @NoArgsConstructor
@Builder
public class Payload implements Serializable {
    State state = null;
    String command = null;

    Set<Flat> flats = null;
    String string = null;

    public Payload(String command, Set<Flat> flats, String string) {
        this.command = command;
        this.flats = flats;
        this.string = string;
    }

    public static Payload empty() {
        return new Payload();
    }

    public static Payload connected() {
        return new Payload(State.CONNECTED,null, null, null);
    }
}