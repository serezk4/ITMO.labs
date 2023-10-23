package com.serezka.scene.entities.action;

import com.serezka.scene.entities.Entity;
import com.serezka.scene.entities.qualifers.Qualifer;
import com.serezka.scene.entities.human.Human;

public interface Action extends Entity {
    String execute();
    String executeFrom(Human... humans);
    String executeFromAndUse(Qualifer qualifer, Human... humans);
    String executeFrom(String entity);
    String executeFromAndUse(Qualifer qualifer, String entity);
}
