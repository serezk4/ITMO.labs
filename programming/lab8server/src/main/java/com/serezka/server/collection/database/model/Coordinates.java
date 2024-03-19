package com.serezka.server.collection.database.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "coordinates")
@Data @AllArgsConstructor @NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Coordinates implements Comparable<Coordinates> {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    /**
     * Максимальное значение поля: 364
     * Поле не может быть null
     */
    @Column(nullable = false)
    @Max(value = 364, message = "X must be less than 364")
    Float x;

    /**
     * Максимальное значение поля: 182
     * Поле не может быть null
     */
    @Column(nullable = false)
    @Max(value = 182, message = "Y must be less than 182")
    Long y;

    // utils methods
    @Override
    public int compareTo(Coordinates o) {
        return x.compareTo(o.getX()) + y.compareTo(o.getY());
    }
}