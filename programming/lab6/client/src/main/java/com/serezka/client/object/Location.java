package com.serezka.client.object;

import com.opencsv.bean.CsvBindByName;
import com.serezka.client.object.exceptions.RequirementsException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter @Builder
public class Location implements Serializable, Comparable<Location> {
    /**
     * Поле не может быть null
     */
    Integer x;

    public Integer setX(Integer x) {
        if (x == null) throw new RequirementsException("x", "Поле не может быть null");
        return x;
    }

    @Setter
    Double y;

    /**
     * Поле не может быть null
     */
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
