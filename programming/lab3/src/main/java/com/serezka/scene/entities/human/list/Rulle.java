package com.serezka.scene.entities.human.list;

import com.serezka.scene.entities.human.Gender;
import com.serezka.scene.entities.human.HumanImpl;
import com.serezka.scene.entities.place.list.FilleAndRulleHouse;

public class Rulle extends HumanImpl {
    private Rulle() {
        super("Рулле", 9, FilleAndRulleHouse.getInstance(), Gender.MALE);
    }

    private static Rulle instance = null;

    public static Rulle getInstance() {
        if (instance == null) instance = new Rulle();
        return instance;
    }
}
