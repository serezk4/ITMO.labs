package com.serezka.server.collection.execution.transfer;

import com.serezka.server.collection.database.model.Flat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Collections;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Setter @Getter
@ToString
public class Response {
    String code;
    List<Flat> flats;

    public Response(String code) {
        this.code = code;
        this.flats = Collections.emptyList();
    }
}
