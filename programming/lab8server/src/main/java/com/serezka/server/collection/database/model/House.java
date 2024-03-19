package com.serezka.server.collection.database.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
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
    @Size(min = 1, message = "Name must not be empty")
    String name;

    /**
     * Значение поля должно быть больше 0
     */
    @Min(value = 1, message = "Year must be greater than 0")
    Integer year;

    /**
     * Значение поля должно быть больше 0
     */
    @Column(name = "flats_on_floor", nullable = false)
    @Min(value = 1, message = "Number of flats on floor must be greater than 0")
    Long numberOfFlatsOnFloor;

    /**
     * Значение поля должно быть больше 0
     */
    @Column(name = "lifts_count")
    @Min(value = 1, message = "Number of lifts must be greater than 0")
    int numberOfLifts;
}
