package com.serezka.lab5.chat.obj;

import com.serezka.lab5.chat.obj.exceptions.RequirementsException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class Product {
    /**
     * Поле не может быть null
     * Значение поля должно быть больше 0
     * Значение этого поля должно быть уникальным
     * Значение этого поля должно генерироваться автоматически
     */
    static Integer counter = 0;
    final Integer id = counter++;
    // always non null

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
     * Поле не может быть null
     */
    Coordinates coordinates;

    public void setCoordinates(Coordinates coordinates) {
        if (coordinates == null) throw new RequirementsException("coordinates", "Поле не может быть null");
        this.coordinates = coordinates;
    }

    /**
     * Поле не может быть null
     * Значение этого поля должно генерироваться автоматически
     */
    final LocalDateTime creationDate = LocalDateTime.now();
    // always non null

    /**
     * Значение поля должно быть больше 0
     */
    float price;

    public void setPrice(float price) {
        if (price < 0) throw new RequirementsException("price", "Значение поля должно быть больше 0");
        this.price = price;
    }

    /**
     * Длина строки не должна быть больше 100
     * Строка не может быть пустой
     * Длина строки должна быть не меньше 13
     * Поле может быть null
     */
    String partNumber;

    public void setPartNumber(String partNumber) {
        if (partNumber == null || partNumber.isBlank() || !(partNumber.length() <= 100 && partNumber.length() >= 13))
            throw new RequirementsException("partNumber", "Длина строки не должна быть больше 100, Строка не может быть пустой, Длина строки должна быть не меньше 13, Поле может быть null");
        this.partNumber = partNumber;
    }

    /**
     * Поле может быть null
     */
    @Setter
    UnitOfMeasure unitOfMeasure;

    /**
     * Поле может быть null
     */
    @Setter
    Person owner;
}
