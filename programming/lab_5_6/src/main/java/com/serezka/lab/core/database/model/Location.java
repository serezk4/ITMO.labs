package com.serezka.lab.core.database.model;

import com.opencsv.bean.CsvBindByName;
import com.serezka.lab.core.database.model.exceptions.RequirementsException;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Deprecated

@Entity
@Table(name = "locations")
@Data @NoArgsConstructor @AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Location implements Serializable, Comparable<Location> {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

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
