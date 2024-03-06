package com.serezka.objects;

import com.serezka.objects.exceptions.RequirementsException;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder @Data
@AllArgsConstructor @NoArgsConstructor
public class House implements Validatable {
    Long id;

    /**
     * Поле может быть null
     */
    String name;

    /**
     * Значение поля должно быть больше 0
     */
    Integer year;

    /**
     * Значение поля должно быть больше 0
     */
    Long numberOfFlatsOnFloor;

    public void setNumberOfFlatsOnFloor(Long numberOfFlatsOnFloor) {
        if (numberOfFlatsOnFloor <= 0) throw new RequirementsException("numberOfFlatsOnFloor", "field must be > 0");
        this.numberOfFlatsOnFloor = numberOfFlatsOnFloor;
    }

    /**
     * Значение поля должно быть больше 0
     */
    int numberOfLifts;

    @Override
    public boolean validate() throws RequirementsException {
        return false;
    }
}
