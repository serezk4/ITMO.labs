package com.serezka.scene.entities.qualifers;

import com.serezka.scene.exceptions.IllegalArgumentException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class QualifierImpl implements Qualifer {
    String qualifier;

    public QualifierImpl(String qualifier) {
        this.qualifier = qualifier;

        validate();
    }

    @Override
    public String getName() {
        return this.qualifier;
    }

    @Override
    public void validate() throws IllegalArgumentException {
        if (qualifier == null || qualifier.isBlank()) throw new IllegalArgumentException();
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
