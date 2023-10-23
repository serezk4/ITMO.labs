package com.serezka.scene.entities.human;

import com.serezka.scene.entities.place.Place;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class HumanImpl implements Human {
    String name;
    int age;

    Place home;
    Gender gender;

    @Override
    public String describe() {
        return String.format("%s, %s, %d лет, живет в %s",
                this.name, this.gender.getName(), this.age, this.home.getName());
    }

    @Override
    public Place getHome() {
        return this.home;
    }

    @Override
    public Gender getGender() {
        return this.gender;
    }

    @Override
    public int getAge() {
        return this.age;
    }

    @Override
    public Organ[] getOrgans() {
        return Organ.values();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getName(String prefix) {
        return String.join(" ", prefix, this.name);
    }

    @Override
    public String toString() {
        return "Человек {" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", home=" + home +
                ", gender=" + gender +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HumanImpl human = (HumanImpl) o;
        return age == human.age && Objects.equals(name, human.name) && Objects.equals(home, human.home) && gender == human.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, home, gender);
    }
}
