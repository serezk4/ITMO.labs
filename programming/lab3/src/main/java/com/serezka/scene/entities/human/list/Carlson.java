package com.serezka.scene.entities.human.list;

import com.serezka.scene.entities.human.Gender;
import com.serezka.scene.entities.human.HumanImpl;
import com.serezka.scene.entities.place.list.CarlsonRoof;

public class Carlson extends HumanImpl {
    private Carlson() {
        super("Карлсон", 30, CarlsonRoof.getInstance(), Gender.MALE);
    }

    private static Carlson instance = null;

    public static Carlson getInstance() {
        if (instance == null) instance = new Carlson();
        return instance;
    }
}
