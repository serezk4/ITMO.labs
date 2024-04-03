package com.serezka.net.execution;

import com.serezka.objects.Flat;
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
    Object[] objects;

    List<Flat> flats;

    public Response(String code) {
        this.code = code;
        this.flats = Collections.emptyList();
    }

    public Response(String code, Object... objects) {
        this.code = code;
        this.objects = objects;
        this.flats = Collections.emptyList();
    }

    public Response(List<Flat> flats, String code, Object[] objects) {
        this.code = code;
        this.objects = objects;
        this.flats = flats;
    }

    public Response(List<Flat> flats,String code) {
        this.code = code;
        this.flats = flats;
    }
}
