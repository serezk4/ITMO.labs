package com.serezka.lab5.chat.object;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import com.serezka.lab5.chat.io.format.converter.CoordinatesConverter;
import com.serezka.lab5.chat.io.format.converter.PersonConverter;
import com.serezka.lab5.chat.object.exceptions.RequirementsException;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.PropertySource;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@PropertySource("${application.properties}")
public class Product implements Serializable, Comparable<Product> {
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
        //todo сравнивать, допустим, по цене 
//        return getId().compareTo(o.getId())+
//                getName().compareTo(o.getName())+
//                coordinates.compareTo(o.getCoordinates())+
//                creationDate.compareTo(o.getCreationDate())+
//                price.compareTo(o.getPrice())+
//                partNumber.compareTo(o.getPartNumber())+
//                unitOfMeasure.compareTo(o.getUnitOfMeasure())+
//                owner.compareTo(o.getOwner());
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
}
