package com.serezka.scene.entities.place;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class PlaceImpl implements Place {
    String name;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getName(String prefix) {
        return String.join(" ", prefix, " ", this.name);
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
