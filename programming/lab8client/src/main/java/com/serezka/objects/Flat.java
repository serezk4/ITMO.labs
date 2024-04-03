package com.serezka.objects;

import com.serezka.objects.exceptions.RequirementsException;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.ZonedDateTime;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flat implements Comparable<Flat>, Validatable {
    /**
     * Поле не может быть null
     * Значение поля должно быть больше 0
     * Значение этого поля должно быть уникальным
     * Значение этого поля должно генерироваться автоматически
     */
    Long id;

    Long userId;

    boolean editable = false;

    /**
     * Поле не может быть null
     * Строка не может быть пустой
     */
    @NonNull
    String name;

    /**
     * Поле не может быть null
     */
    @NonNull
    Coordinates coordinates;

    /**
     * Поле не может быть null
     * Значение этого поля должно генерироваться автоматически
     */
    @Builder.Default
    ZonedDateTime creationDate = ZonedDateTime.now();

    /**
     * Максимальное значение поля: 734
     * Значение поля должно быть больше 0
     */
    @NonNull
    Long area;

    public void setArea(@NonNull Long area) {
        if (area > 734 || area < 0)
            throw new RequirementsException("area", "field can pick only number in range [0,734]");
        this.area = area;
    }

    /**
     * Поле не может быть null
     * Значение поля должно быть больше 0
     */
    @NonNull
    Integer numberOfRooms;

    public void setNumberOfRooms(@NonNull Integer numberOfRooms) {
        if (numberOfRooms <= 0) throw new RequirementsException("numberOfRooms", "field must be > 0");
        this.numberOfRooms = numberOfRooms;
    }

    /**
     * Значение поля должно быть больше 0
     */
    Double livingSpace;

    public void setLivingSpace(Double livingSpace) {
        if (livingSpace <= 0) throw new RequirementsException("livingSpace", "field must be > 0");
        this.livingSpace = livingSpace;
    }

    boolean furniture;

    /**
     * Поле может быть null
     */
    Transport transport;

    /**
     * Поле не может быть null
     */
    @NonNull
    House house;

    @Override
    public int compareTo(Flat o) {
        return this.livingSpace.compareTo(o.livingSpace) + (this.furniture && o.furniture ? 1 : 0);
    }

    public static Flat generate() {
        return new Flat(); // todo
    }

    @Override
    public boolean validate() throws RequirementsException {
        return false;
    }
}

