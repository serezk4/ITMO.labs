package com.serezka.lab5.chat.obj;

import com.opencsv.bean.CsvBindByName;
import com.serezka.lab5.chat.obj.exceptions.RequirementsException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter @Builder
public class Location implements Serializable {
    /**
     * Поле не может быть null
     */
    @CsvBindByName(column = "x", required = true)
    Integer x;

    public Integer setX(Integer x) {
        if (x == null) throw new RequirementsException("x", "Поле не может быть null");
        return x;
    }

    @CsvBindByName(column = "y", required = true)
    @Setter
    double y;

    /**
     * Поле не может быть null
     */
    @CsvBindByName(column = "z", required = true)
    Integer z;

    public void setZ(Integer z) {
        if (z==null) throw new RequirementsException("z", "Поле не может быть null");
        this.z = z;
    }

    public double sum() {
        return x + y + z;
    }
}
