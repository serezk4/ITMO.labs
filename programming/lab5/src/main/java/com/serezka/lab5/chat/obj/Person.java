package com.serezka.lab5.chat.obj;

import com.opencsv.bean.CsvBindByName;
import com.serezka.lab5.chat.obj.exceptions.RequirementsException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter @Builder
public class Person implements Serializable {
    /**
     * Поле не может быть null
     * Строка не может быть пустой
     */
    @CsvBindByName(column = "person_name", required = true)
    String name;

    public void setName(String name) {
        if (name == null || name.isBlank()) throw new RequirementsException("name", "Поле не может быть null, Строка не может быть пустой");
        this.name = name;
    }

    /**
     * Поле может быть null
     * Значение поля должно быть больше 0
     */
    @CsvBindByName(column = "height", required = true)
    Long height;

    public void setHeight(Long height) {
        if (height == null || height < 0) throw new RequirementsException("height", "Поле может быть null, Значение поля должно быть больше 0");
        this.height = height;
    }

    /**
     * Поле может быть null
     */
    @CsvBindByName(column = "eye_color")
    @Setter
    Color eyeColor;

    /**
     * Поле может быть null
     */
    @Setter
    @CsvBindByName(column = "hair_color")
    Color hairColor;

    /**
     * Поле может быть null
     */
    @CsvBindByName(column = "location")
    @Setter
    Location location;

}
