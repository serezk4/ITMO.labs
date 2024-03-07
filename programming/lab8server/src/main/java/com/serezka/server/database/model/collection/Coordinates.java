package com.serezka.server.database.model.collection;

import jakarta.persistence.*;
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
    Float x;

    /**
     * Максимальное значение поля: 182
     * Поле не может быть null
     */
    Long y;

    // utils methods
    @Override
    public int compareTo(Coordinates o) {
        return x.compareTo(o.getX()) + y.compareTo(o.getY());
    }
}