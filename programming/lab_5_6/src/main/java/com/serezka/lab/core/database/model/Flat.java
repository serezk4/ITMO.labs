package com.serezka.lab.core.database.model;

import com.opencsv.bean.CsvBindByName;
import com.serezka.lab.core.database.model.exceptions.RequirementsException;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;

@Entity @Table(name = "flats")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder @Data
@AllArgsConstructor @NoArgsConstructor
public class Flat implements Comparable<Flat>, Validatable {
    /**
     * Поле не может быть null
     * Значение поля должно быть больше 0
     * Значение этого поля должно быть уникальным
     * Значение этого поля должно генерироваться автоматически
     */
    @CsvBindByName(column = "id", required = true)
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @CsvBindByName(column = "user_id")
    @Column(name = "user_id", nullable = false)
    Long userId;

    /**
     * Поле не может быть null
     * Строка не может быть пустой
     */
    @NonNull
    @CsvBindByName(column = "name", required = true)
    @Column(nullable = false)
    String name;

    /**
     * Поле не может быть null
     */
    @NonNull
//    @CsvBindByName todo
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coordinates", referencedColumnName = "id")
    Coordinates coordinates;

    /**
     * Поле не может быть null
     * Значение этого поля должно генерироваться автоматически
     */
    @NonNull
    @CsvBindByName(column = "creation_date")
    @Column(name = "creation_date")
    Date creationDate = new Date();

    /**
     * Максимальное значение поля: 734
     * Значение поля должно быть больше 0
     */
    @NonNull
    @CsvBindByName(column = "area")
    Long area;

    public void setArea(@NonNull Long area) {
        if (area > 734 || area < 0) throw new RequirementsException("area", "field can pick only number in range [0,734]");
        this.area = area;
    }

    /**
     * Поле не может быть null
     * Значение поля должно быть больше 0
     */
    @NonNull
    @CsvBindByName(column = "number_of_rooms")
    @Column(name = "number_of_rooms")
    Integer numberOfRooms;

    public void setNumberOfRooms(@NonNull Integer numberOfRooms) {
        if (numberOfRooms <= 0) throw new RequirementsException("numberOfRooms", "field must be > 0");
        this.numberOfRooms = numberOfRooms;
    }

    /**
     * Значение поля должно быть больше 0
     */
    @CsvBindByName(column = "living_space")
    @Column(name = "living_space")
    Double livingSpace;

    public void setLivingSpace(Double livingSpace) {
        if (livingSpace <= 0) throw new RequirementsException("livingSpace", "field must be > 0");
        this.livingSpace = livingSpace;
    }

    @CsvBindByName(column = "furniture")
    boolean furniture;

    /**
     * Поле может быть null
     */
    @CsvBindByName(column = "transport")
    Transport transport;

    /**
     * Поле не может быть null
     */
    @NonNull
//    @CsvBindByName(column = ) todo
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "houses", referencedColumnName = "id")
    House house;

    @Override
    public int compareTo(Flat o) {
        return this.livingSpace.compareTo(o.livingSpace) + (this.furniture && o.furniture ? 1 : 0);
    }

    public static Flat generate() {
        return new Flat(); // todo
    }

    @Override
    public boolean validate() throws RequirementsException {
        return false;
    }
}

