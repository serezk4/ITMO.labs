package com.serezka.scene.entities.qualifers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class QualifierImpl implements Qualifer {
    String qualifier;

    @Override
    public String getName() {
        return this.qualifier;
    }

    @Override
    public String toString() {
        return "Qualifer {" +
                "qualifier='" + qualifier + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QualifierImpl qualifer = (QualifierImpl) o;
        return Objects.equals(qualifier, qualifer.qualifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(qualifier);
    }
}
