package com.serezka.scene.entities.human;

import com.serezka.scene.entities.Entity;
import com.serezka.scene.entities.place.Place;

public interface Human extends Entity {
    String describe();

    Place getHome();
    Gender getGender();
    int getAge();

    Organ[] getOrgans();
}
