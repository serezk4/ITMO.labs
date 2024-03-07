package com.serezka.lab.core.v2.api.objects;

import com.serezka.lab.core.database.model.Flat;
import com.serezka.lab.core.database.model.User;
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
    User user;

    String command = null;

    List<Flat> flats = null;
    String string = null;
}