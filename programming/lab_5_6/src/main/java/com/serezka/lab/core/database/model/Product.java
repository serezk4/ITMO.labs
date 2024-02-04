package com.serezka.lab.core.database.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import com.serezka.lab.core.database.model.exceptions.RequirementsException;
import com.serezka.lab.core.io.format.csv.converter.CoordinatesConverter;
import com.serezka.lab.core.io.format.csv.converter.PersonConverter;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Deprecated

@Entity
@Table(name = "products")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable, Comparable<Product> {
    /**
     * Поле не может быть null
     * Значение поля должно быть больше 0
     * Значение этого поля должно быть уникальным
     * Значение этого поля должно генерироваться автоматически
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    // always non null

    @Column(name = "user_id")
    Long userId;

    /**
     * Поле не может быть null
     * Строка не может быть пустой
     */
    @CsvBindByName(column = "name", required = true)
    @Column(nullable = false)
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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coordinates", referencedColumnName = "id")
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
    @Column(nullable = false)
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
    @Column(nullable = false)
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
    @Column(nullable = false)
    @Setter
    UnitOfMeasure unitOfMeasure;

    /**
     * Поле может быть null
     */
    @CsvCustomBindByName(column = "owner", converter = PersonConverter.class, required = false)
    @PrimaryKeyJoinColumn(name = "persons", referencedColumnName = "id")
    @OneToOne(cascade = CascadeType.ALL)
    @Setter
    Person owner;

    // helpful methods
    @Override
    public int compareTo(Product o) {
        return getPrice().compareTo(o.getPrice());
    }

    @Override
    public String toString() {
        return "Product\n" +
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
                "\t location: \n" +
                (owner.getLocation() != null ?
                        "\t\t x : " + owner.getLocation().getX() + '\n' +
                                "\t\t y : " + owner.getLocation().getY() + '\n' +
                                "\t\t z : " + owner.getLocation().getZ() + '\n' : "not specified"
                );
    }

    private static final String[] HUMAN_NAMES = new String[]{"Wade", "Dave", "Seth", "Ivan", "Riley", "Gilbert", "Jorge", "Dan", "Brian", "Roberto", "Ramon", "Miles", "Liam", "Nathaniel", "Ethan", "Lewis", "Milton", "Claude", "Joshua", "Glen", "Harvey", "Blake", "Antonio", "Connor", "Julian", "Aidan", "Harold", "Conner", "Peter", "Hunter", "Eli", "Alberto", "Carlos", "Shane", "Aaron", "Marlin", "Paul", "Ricardo", "Hector", "Alexis", "Adrian", "Kingston", "Douglas", "Gerald", "Joey", "Johnny", "Charlie", "Scott", "Martin", "Tristin", "Troy", "Tommy", "Rick", "Victor", "Jessie", "Neil", "Ted", "Nick", "Wiley", "Morris", "Clark", "Stuart", "Orlando", "Keith", "Marion", "Marshall", "Noel", "Everett", "Romeo", "Sebastian", "Stefan", "Robin", "Clarence", "Sandy", "Ernest", "Samuel", "Benjamin", "Luka", "Fred", "Albert", "Greyson", "Terry", "Cedric", "Joe", "Paul", "George", "Bruce", "Christopher", "Mark", "Ron", "Craig", "Philip", "Jimmy", "Arthur", "Jaime", "Perry", "Harold", "Jerry", "Shawn", "Walter"};
    private static final String[] PRODUCT_NAMES = new String[]{"wheat", "rye", "oats", "corn", "rice", "bakery goods", "bread", "rolls", "cakes", "cookies", "pies", "cereal", "corn", "flakes", "oat", "flakes", "popcorn", "bread", "sesame roll", "cinnamon roll", "hamburger bun", "hot dog bun", "birthday cake", "wedding cake", "Christmas cake", "chocolate cake", "coffee cake", "oatmeal cookie", "chocolate cookie", "crackers", "biscuits", "toast", "apple pie", "meat pie", "pizza", "pancake", "doughnut", "muffin", "beef", "pork", "veal", "lamb", "beefsteak", "roast", "beef", "ground", "beef", "hamburgers", "pork", "chops", "lamb", "chops"};

    public static Product generate() {
        final String productName = PRODUCT_NAMES[(int) (PRODUCT_NAMES.length * Math.random())];
        String partNumber = String.valueOf(productName.hashCode()).repeat(5);
        partNumber = partNumber.substring(0, Math.min(partNumber.length(), 99));

        return Product.builder()
                .name(productName)
                .coordinates(Coordinates.builder()
                        .x((float) (Math.random() * 364))
                        .y((long) (Math.random() * 182))
                        .build())
                .price((float) (1 + Math.random() * 400))
                .partNumber(partNumber)
                .unitOfMeasure(UnitOfMeasure.values()[(int) (Math.random() * UnitOfMeasure.values().length)])
                .owner(Person.builder()
                        .name(HUMAN_NAMES[(int) (HUMAN_NAMES.length * Math.random())])
                        .height((long) (50 + Math.random() * 150))
                        .eyeColor(Color.values()[(int) (Color.values().length * Math.random())])
                        .hairColor(Color.values()[(int) (Color.values().length * Math.random())])
                        .location(Location.builder()
                                .x((int) (Math.random() * 1000))
                                .y(Math.random() * 1000)
                                .z((int) (Math.random() * 1000))
                                .build())
                        .build())
                .build();
    }
}
