package com.serezka.scene.entities.human;

import com.serezka.scene.entities.Entity;
import com.serezka.scene.entities.action.Action;
import com.serezka.scene.entities.place.Place;
import com.serezka.scene.entities.qualifers.Qualifer;

public interface Human extends Entity {
    String describe();

    Place getHome();
    Gender getGender();
    int getAge();

    Organ[] getOrgans();

    String action(Action action);
    String actionWith(Action action, Human... humans);
    String qualify(Qualifer qualifer);
    String qualifyWith(Qualifer qualifer, Human... humans);
}
