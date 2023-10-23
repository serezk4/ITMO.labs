package com.serezka.scene.entities.qualifers;

import com.serezka.scene.entities.Entity;
import com.serezka.scene.entities.human.Human;

public interface Qualifer extends Entity {
    String from();
    String use(Human... humans);
    String use(String entity);
    String use();
}
