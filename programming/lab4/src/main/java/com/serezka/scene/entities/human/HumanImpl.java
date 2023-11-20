package com.serezka.scene.entities.human;

import com.serezka.scene.entities.action.Action;
import com.serezka.scene.entities.place.Place;
import com.serezka.scene.entities.qualifers.Qualifer;
import com.serezka.scene.entities.util.HumanUtils;
import com.serezka.scene.exceptions.IllegalArgumentException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class HumanImpl implements Human {
    String name;
    int age;

    Place home;
    Gender gender;

    public HumanImpl(String name, int age, Place home, Gender gender) {
        this.name = name;
        this.age = age;
        this.home = home;
        this.gender = gender;

        validate();
    }

    @Override
    public void validate() throws IllegalArgumentException {
        if (age > 150 || age < 0) throw new IllegalArgumentException("age must be in range: 0 < age < 150");
        if (name == null || name.isBlank() || gender == null) throw new IllegalArgumentException();
    }

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
    public String action(Action action) {
        return String.join(" ", this.name,  action.getName());
    }

    @Override
    public String actionWith(Action action, Human... humans) {
        return String.join(" ", HumanUtils.toString(humans), "и", this.name, action.getName());
    }

    @Override
    public String qualify(Qualifer qualifer) {
        return String.join(" ", this.name, qualifer.getName());
    }

    @Override
    public String qualifyWith(Qualifer qualifer, Human... humans) {
        return String.join(" ", HumanUtils.toString(humans), "и", this.name, qualifer.getName());
    }

    @Override
    public String getName() {
        return this.name;
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
