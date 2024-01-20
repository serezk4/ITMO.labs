package com.serezka.lab5.chat.obj;

import com.serezka.lab5.chat.obj.exceptions.RequirementsException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class Coordinates {
    /**
     * Максимальное значение поля: 364
     * Поле не может быть null
     */
    Float x;

    public void setX(Float x) {
        if (x == null || x > 364) throw new RequirementsException("x", "Максимальное значение поля: 364, Поле не может быть null");
        this.x = x;
    }

    /**
     * Максимальное значение поля: 182
     * Поле не может быть null
     */
    Long y;

    public void setY(Long y) {
        if (y == null || y > 182) throw new RequirementsException("y", "Максимальное значение поля: 182, Поле не может быть null");
        this.y = y;
    }
}