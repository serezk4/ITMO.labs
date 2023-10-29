package com.serezka.scene.entities.place.list;

public class FilleAndRulleHouse extends House {
    private FilleAndRulleHouse() {}

    private static FilleAndRulleHouse instance = null;

    public static FilleAndRulleHouse getInstance() {
        if (instance == null) instance = new FilleAndRulleHouse();
        return instance;
    }
}
