package com.serezka.scene.entities.human.list;

import com.serezka.scene.entities.human.Gender;
import com.serezka.scene.entities.human.HumanImpl;
import com.serezka.scene.entities.place.list.FilleAndRulleHouse;

public class Fille extends HumanImpl {
    private Fille() {
        super("Филле", 9, FilleAndRulleHouse.getInstance(), Gender.MALE);
    }

    private static Fille instance = null;

    public static Fille getInstance() {
        if (instance == null) instance = new Fille();
        return instance;
    }
}
