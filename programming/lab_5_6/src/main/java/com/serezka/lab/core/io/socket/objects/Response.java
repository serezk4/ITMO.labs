package com.serezka.lab.core.io.socket.objects;

import com.serezka.lab.core.database.model.Flat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data @AllArgsConstructor @NoArgsConstructor
@Builder
public class Response {
    State state = null;

    String message = null;
    Set<Flat> flats = null;

    public Response(String message, Set<Flat> flats) {
        this.state = State.OK;
        this.message = message;
        this.flats = flats;
    }

    public Response(String message) {
        this.message = message;
    }

    public static Response connected() {
        return new Response(State.CONNECTED, null, null);
    }
}
