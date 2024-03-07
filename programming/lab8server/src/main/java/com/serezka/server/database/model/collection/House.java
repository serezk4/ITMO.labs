package com.serezka.server.database.model.collection;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity @Table(name = "houses")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder @Data
@AllArgsConstructor @NoArgsConstructor
public class House {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    /**
     * Поле может быть null
     */
    String name;

    /**
     * Значение поля должно быть больше 0
     */
    Integer year;

    /**
     * Значение поля должно быть больше 0
     */
    @Column(name = "number_of_flats_on_floor", nullable = false)
    Long numberOfFlatsOnFloor;

    /**
     * Значение поля должно быть больше 0
     */
    @Column(name = "number_of_lifts")
    int numberOfLifts;
}
