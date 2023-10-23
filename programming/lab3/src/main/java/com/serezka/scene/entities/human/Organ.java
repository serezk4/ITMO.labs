package com.serezka.scene.entities.human;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
public enum Organ {
    EYES("глаза");

    String name;

    Organ(String name) {
        this.name = name;
    }
}
