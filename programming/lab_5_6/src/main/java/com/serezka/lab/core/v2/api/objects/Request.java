package com.serezka.lab.core.v2.api.objects;

import com.serezka.lab.core.database.model.Flat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Request implements Serializable {
    final String token;

    String command = null;

    List<Flat> flats = null;
    String string = null;
}