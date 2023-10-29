package com.serezka.scene.entities.family;

import com.serezka.scene.entities.Entity;
import com.serezka.scene.entities.human.Human;
import com.serezka.scene.entities.place.Place;

import java.util.List;

public interface Family extends Entity {
    List<Human> getPeoples();
    Place getHouse();
}
