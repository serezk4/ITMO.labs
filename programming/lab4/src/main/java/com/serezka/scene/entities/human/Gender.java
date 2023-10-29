package com.serezka.scene.entities.human;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum Gender {
    MALE("он", "мужчина"),
    FEMALE("она", "женщина");

    String prefix;
    String name;

    Gender(String prefix, String name) {
        this.prefix = prefix;
        this.name = name;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getName() {
        return name;
    }
}
