package com.serezka.net.execution;

import com.serezka.objects.Flat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor @NoArgsConstructor
@Setter @Getter
@ToString
public class Request {
    String command;
    List<Flat> flats;
    String text;
}
