package com.serezka.lab5.chat.obj;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import com.serezka.lab5.chat.file_worker.converter.CoordinatesConverter;
import com.serezka.lab5.chat.file_worker.converter.PersonConverter;
import com.serezka.lab5.chat.obj.exceptions.RequirementsException;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.PropertySource;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter @Builder
@AllArgsConstructor @NoArgsConstructor
@PropertySource("${application.properties}")
@ToString
public class Product implements Serializable {
    /**
     * Поле не может быть null
     * Значение поля должно быть больше 0
     * Значение этого поля должно быть уникальным
     * Значение этого поля должно генерироваться автоматически
     */
    static Integer counter = 0;

    @CsvBindByName(column = "id", required = true)
    @Setter
    Integer id = counter++;
    // always non null

    /**
     * Поле не может быть null
     * Строка не может быть пустой
     */
    @CsvBindByName(column = "name", required = true)
    String name;

    public void setName(String name) {
        if (name == null || name.isBlank())
            throw new RequirementsException("name", "Поле не может быть null, Строка не может быть пустой");
        this.name = name;
    }

    /**
     * Поле не может быть null
     */
    @CsvCustomBindByName(column = "coordinates", converter = CoordinatesConverter.class, required = true)
    Coordinates coordinates;

    public void setCoordinates(Coordinates coordinates) {
        if (coordinates == null) throw new RequirementsException("coordinates", "Поле не может быть null");
        this.coordinates = coordinates;
    }

    /**
     * Поле не может быть null
     * Значение этого поля должно генерироваться автоматически
     */
    @CsvBindByName(column = "creation_date", required = true)
    @Setter
    String creationDate = new SimpleDateFormat("dd.MM.yyyy hh:mm").format(new Date());
    // always non null

    /**
     * Значение поля должно быть больше 0
     */
    @CsvBindByName(column = "price", required = true)
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
    @CsvBindByName(column = "part_number", required = true)
    String partNumber;

    public void setPartNumber(String partNumber) {
        if (partNumber == null || partNumber.isBlank() || !(partNumber.length() <= 100 && partNumber.length() >= 13))
            throw new RequirementsException("partNumber", "Длина строки не должна быть больше 100, Строка не может быть пустой, Длина строки должна быть не меньше 13, Поле может быть null");
        this.partNumber = partNumber;
    }

    /**
     * Поле может быть null
     */
    @CsvBindByName(column = "unit_of_measure", required = false)
    @Setter
    UnitOfMeasure unitOfMeasure;

    /**
     * Поле может быть null
     */
    @CsvCustomBindByName(column = "owner", converter = PersonConverter.class, required = false)
    @Setter
    Person owner;
}
