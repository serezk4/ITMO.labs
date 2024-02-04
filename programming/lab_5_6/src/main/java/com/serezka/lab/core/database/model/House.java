package com.serezka.lab.core.database.model;

import com.serezka.lab.core.database.model.exceptions.RequirementsException;
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
    @Column(name = "number_of_flats_on_floor")
    Long numberOfFlatsOnFloor;

    public void setNumberOfFlatsOnFloor(Long numberOfFlatsOnFloor) {
        if (numberOfFlatsOnFloor <= 0) throw new RequirementsException("numberOfFlatsOnFloor", "field must be > 0");
        this.numberOfFlatsOnFloor = numberOfFlatsOnFloor;
    }

    /**
     * Значение поля должно быть больше 0
     */
    @Column(name = "number_of_lifts")
    int numberOfLifts;
}
