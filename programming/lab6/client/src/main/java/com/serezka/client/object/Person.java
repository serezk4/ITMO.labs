package com.serezka.client.object;

import com.opencsv.bean.CsvBindByName;
import com.serezka.client.object.exceptions.RequirementsException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter @Builder
public class Person implements Serializable, Comparable<Person> {
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

    // helpful methods
    @Override
    public int compareTo(Person o) {
        return name.compareTo(o.getName())+
                eyeColor.compareTo(o.getEyeColor())+
                hairColor.compareTo(o.getHairColor())+
                location.compareTo(o.getLocation());
    }
}
