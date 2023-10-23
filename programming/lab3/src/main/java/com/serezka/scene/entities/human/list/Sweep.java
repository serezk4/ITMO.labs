package com.serezka.scene.entities.human.list;

import com.serezka.scene.entities.human.Gender;
import com.serezka.scene.entities.human.HumanImpl;

public class Sweep extends HumanImpl {
    private Sweep() {
        super("трубочиста", 50, null, Gender.MALE);
    }

    private static Sweep instance = null;

    public static Sweep getInstance() {
        if (instance == null) instance = new Sweep();
        return instance;
    }
}
