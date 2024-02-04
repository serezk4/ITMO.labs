package com.serezka.lab.core.database.model;

import com.opencsv.bean.CsvBindByName;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity @Table(name = "flats")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder @Data
@AllArgsConstructor @NoArgsConstructor
public class Flat {
    /**
     * Поле не может быть null
     * Значение поля должно быть больше 0
     * Значение этого поля должно быть уникальным
     * Значение этого поля должно генерироваться автоматически
     */
    @CsvBindByName(column = "id", required = true)
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    /**
     * Поле не может быть null
     * Строка не может быть пустой
     */
    @CsvBindByName(column = "name", required = true)
    @Column(nullable = false)
    String name;

    /**
     * Поле не может быть null
     */
//    @CsvBindByName todo
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coordinates", referencedColumnName = "id")
    Coordinates coordinates;

    /**
     * Поле не может быть null
     * Значение этого поля должно генерироваться автоматически
     */
    @CsvBindByName(column = "creation_date")
    @Column(name = "creation_date")
    LocalDate creationDate = LocalDate.now();

    /**
     * Максимальное значение поля: 734
     * Значение поля должно быть больше 0
     */
    @CsvBindByName(column = "area")
    Long area;

    /**
     * Поле не может быть null
     * Значение поля должно быть больше 0
     */
    @CsvBindByName(column = "number_of_rooms")
    @Column(name = "number_of_rooms")
    Integer numberOfRooms;

    /**
     * Значение поля должно быть больше 0
     */
    @CsvBindByName(column = "living_space")
    @Column(name = "living_space")
    Double livingSpace;

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
//    @CsvBindByName(column = ) todo
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "houses", referencedColumnName = "id")
    House house;
}

