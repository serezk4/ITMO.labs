package com.serezka.lab5.chat.obj;

import com.serezka.lab5.chat.obj.exceptions.RequirementsException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class Person {
    /**
     * Поле не может быть null
     * Строка не может быть пустой
     */
    String name;

    public void setName(String name) {
        if (name == null || name.isBlank()) throw new RequirementsException("name", "Поле не может быть null, Строка не может быть пустой");
        this.name = name;
    }

    /**
     * Поле может быть null
     * Значение поля должно быть больше 0
     */
    Long height;

    public void setHeight(Long height) {
        if (height == null || height < 0) throw new RequirementsException("height", "Поле может быть null, Значение поля должно быть больше 0");
        this.height = height;
    }

    /**
     * Поле может быть null
     */
    @Setter
    Color eyeColor;

    /**
     * Поле может быть null
     */
    @Setter
    Color hairColor;

    /**
     * Поле может быть null
     */
    @Setter
    Location location;

}
