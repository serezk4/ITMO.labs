package com.serezka.lab.core.v2.api.objects;

import com.serezka.lab.core.database.model.Flat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data @AllArgsConstructor @NoArgsConstructor
@Builder
public class Response {
    String code = null;
    Set<Flat> flats = null;

    Object[] formatData = new Object[0];

    public Response(String code, Set<Flat> flats) {
        this.code = code;
        this.flats = flats;
    }

    public Response(String code) {
        this.code = code;
    }

    public Response(String code, Object... formatData) {
        this.code = code;
        this.formatData = formatData;
    }
}
