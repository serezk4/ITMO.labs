package com.serezka.objects;

import com.serezka.objects.exceptions.RequirementsException;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Coordinates implements Serializable, Comparable<Coordinates> {
    Long id;

    /**
     * Максимальное значение поля: 364
     * Поле не может быть null
     */
    @Getter
    Float x;

    public void setX(Float x) {
        if (x == null || x > 364)
            throw new RequirementsException("x", "Максимальное значение поля: 364, Поле не может быть null");
        this.x = x;
    }

    /**
     * Максимальное значение поля: 182
     * Поле не может быть null
     */
    @Getter
    Long y;

    public void setY(Long y) {
        if (y == null || y > 182)
            throw new RequirementsException("y", "Максимальное значение поля: 182, Поле не может быть null");
        this.y = y;
    }

    // utils methods
    @Override
    public int compareTo(Coordinates o) {
        return x.compareTo(o.getX()) + y.compareTo(o.getY());
    }

    @Override
    public String toString() {
        return x + " | " + y;
    }
}