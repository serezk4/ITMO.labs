package com.serezka.scene.entities.family;

import com.serezka.scene.entities.human.Human;
import com.serezka.scene.entities.place.Place;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Objects;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
 @RequiredArgsConstructor
public class FamilyImpl implements Family {
    String name;
    List<Human> peoples;
    Place house;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getName(String prefix) {
        return String.join(" ", prefix, this.name);
    }

    @Override
    public List<Human> getPeoples() {
        return this.peoples;
    }

    @Override
    public Place getHouse() {
        return this.house;
    }

    @Override
    public String toString() {
        return "Семья {" +
                "name='" + name + '\'' +
                ", peoples=" + peoples +
                ", house=" + house +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FamilyImpl another = (FamilyImpl) o;
        return this.name.equals(another.name) &&
                this.peoples.equals(another.peoples) &&
                this.house.equals(another.house);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, peoples, house);
    }
}
