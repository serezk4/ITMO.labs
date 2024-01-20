package com.serezka.lab5.chat.obj;

import com.serezka.lab5.chat.obj.exceptions.RequirementsException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class Location {
    /**
     * Поле не может быть null
     */
    Integer x;

    public Integer setX(Integer x) {
        if (x == null) throw new RequirementsException("x", "Поле не может быть null");
        return x;
    }

    @Setter
    double y;

    /**
     * Поле не может быть null
     */
    Integer z;

    public void setZ(Integer z) {
        if (z==null) throw new RequirementsException("z", "Поле не может быть null");
        this.z = z;
    }
}
