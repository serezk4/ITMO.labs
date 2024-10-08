package com.serezka.server.controller.object;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import com.serezka.server.controller.io.format.csv.converter.CoordinatesConverter;
import com.serezka.server.controller.io.format.csv.converter.PersonConverter;
import com.serezka.server.controller.object.exceptions.RequirementsException;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.PropertySource;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@PropertySource("${application.properties}")
public class Product implements Serializable, Comparable<Product>, Generatable<Product> {
    /**
     * Поле не может быть null
     * Значение поля должно быть больше 0
     * Значение этого поля должно быть уникальным
     * Значение этого поля должно генерироваться автоматически
     */
    static Integer counter = 0;

    @CsvBindByName(column = "id", required = true)
    @Builder.Default
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
    @Builder.Default
    @Setter
    String creationDate = new SimpleDateFormat("dd.MM.yyyy hh:mm").format(new Date());
    // always non null

    /**
     * Значение поля должно быть больше 0
     */
    @CsvBindByName(column = "price", required = true)
    Float price;

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

    // helpful methods
    @Override
    public int compareTo(Product o) {
        return getPrice().compareTo(o.getPrice());
    }

    @Override
    public String toString() {
        return "Product{\n" +
                "id: " + id + '\n' +
                "name: " + name + '\n' +
                "coordinates: " + '\n' +
                "\t x: " + coordinates.getX() + '\n' +
                "\t y: " + coordinates.getY() + '\n' +
                "creationDate: " + creationDate + '\n' +
                "price: " + price + '\n' +
                "partNumber: " + partNumber + '\n' +
                "unitOfMeasure: " + unitOfMeasure + '\n' +
                "owner: " + '\n' +
                "\t name: " + owner.getName() + '\n' +
                "\t height: " + owner.getHeight() + '\n' +
                "\t eyeColor: " + owner.getEyeColor() + '\n' +
                "\t hairColor: " + owner.getHairColor() + '\n' +
                "\t location: " +
                (owner.getLocation() != null ?
                        "\t\t x : " + owner.getLocation().getX() + '\n' +
                                "\t\t y : " + owner.getLocation().getY() + '\n' +
                                "\t\t z : " + owner.getLocation().getZ() + '\n' : "not specified"
                ) + '\n' +
                '}';
    }

    @Override
    public Product generate() {
        return Product.builder()
                .name(IntStream.range(0, 100).map(q -> 'q').mapToObj(String::valueOf).collect(Collectors.joining()))
                .coordinates(Coordinates.builder()
                        .x((float) (Math.random() * 364))
                        .y((long) (Math.random() * 182))
                        .build())
                .price((float) (1 + Math.random() * 400))
                .partNumber(IntStream.range(13, 40).mapToObj(String::valueOf).collect(Collectors.joining()))
                .unitOfMeasure(UnitOfMeasure.values()[(int) (Math.random() * UnitOfMeasure.values().length)])
                .owner(Person.builder()
                        .name("Josh")
                        .height(152L)
                        .eyeColor(Color.BLACK)
                        .hairColor(Color.WHITE)
                        .location(Location.builder()
                                .x((int) (Math.random() * 1000))
                                .y(Math.random() * 1000)
                                .z((int) (Math.random() * 1000))
                                .build())
                        .build())
                .build();
    }
}
