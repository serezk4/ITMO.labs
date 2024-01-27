package com.serezka.server.controller.object;

import com.opencsv.bean.CsvBindByName;
import com.serezka.server.controller.object.exceptions.RequirementsException;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor @Data
public class Location implements Serializable, Comparable<Location> {
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
    Double y;

    /**
     * Поле не может быть null
     */
    @CsvBindByName(column = "z", required = true)
    Integer z;

    public void setZ(Integer z) {
        if (z==null) throw new RequirementsException("z", "Поле не может быть null");
        this.z = z;
    }

    // helpful methods
    @Override
    public int compareTo(Location o) {
        return x.compareTo(o.getX())+y.compareTo(o.getY())+z.compareTo(o.getZ());
    }
}
