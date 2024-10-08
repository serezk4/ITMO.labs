package com.serezka.server.controller.object;

import com.opencsv.bean.CsvBindByName;
import com.serezka.server.controller.object.exceptions.RequirementsException;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor @NoArgsConstructor @Data
public class Coordinates implements Serializable, Comparable<Coordinates> {
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