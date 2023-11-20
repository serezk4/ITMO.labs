package com.serezka.scene.entities.place;

import com.serezka.scene.exceptions.IllegalArgumentException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PlaceImpl implements Place {
    String name;

    public PlaceImpl(String name) {
        this.name = name;
        validate();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void validate() throws IllegalArgumentException {
        if (name == null || name.isBlank()) throw new IllegalArgumentException();
    }

    @Override
    public String toString() {
        return "Место {" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlaceImpl place = (PlaceImpl) o;
        return Objects.equals(name, place.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
