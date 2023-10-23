package com.serezka.scene.entities.human.list;

import com.serezka.scene.entities.human.Gender;
import com.serezka.scene.entities.human.HumanImpl;
import com.serezka.scene.entities.place.list.KidHouse;

public class Kid extends HumanImpl {
    private Kid() {
        super("Малыш", 10, KidHouse.getInstance(), Gender.MALE);
    }
    private static Kid instance = null;

    public static Kid getInstance() {
        if (instance == null) instance = new Kid();
        return instance;
    }

    @Override
    public String getName(String prefix) {
        return String.join(" ", prefix, super.getName() + "а");
    }
}
