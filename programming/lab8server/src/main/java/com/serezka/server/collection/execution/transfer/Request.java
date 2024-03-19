package com.serezka.server.collection.execution.transfer;

import com.serezka.server.collection.database.model.Flat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Setter @Getter
@ToString
public class Request {
    String command;
    List<Flat> flats;
    String text;
}
