package com.serezka.lab.core.database.model;

import com.opencsv.bean.CsvBindByName;
import com.serezka.lab.core.database.model.exceptions.RequirementsException;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Entity
@Table(name = "coordinates")
@Data @AllArgsConstructor @NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Coordinates implements Serializable, Comparable<Coordinates> {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    /**
     * Максимальное значение поля: 364
     * Поле не может быть null
     */
    @CsvBindByName(column = "x", required = true)
    Float x;

    public void setX(Float x) {
        if (x == null || x > 364) throw new RequirementsException("x", "Максимальное значение поля: 364, Поле не может быть null");
        this.x = x;
    }

    /**
     * Максимальное значение поля: 182
     * Поле не может быть null
     */
    @CsvBindByName(column = "y", required = true)
    Long y;

    public void setY(Long y) {
        if (y == null || y > 182) throw new RequirementsException("y", "Максимальное значение поля: 182, Поле не может быть null");
        this.y = y;
    }

    // utils methods
    @Override
    public int compareTo(Coordinates o) {
        return x.compareTo(o.getX()) + y.compareTo(o.getY());
    }
}