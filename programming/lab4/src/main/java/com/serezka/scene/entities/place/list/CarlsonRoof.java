package com.serezka.scene.entities.place.list;

public class CarlsonRoof extends Roof {
    private CarlsonRoof() {}
    private static CarlsonRoof instance = null;

    public static CarlsonRoof getInstance() {
        if (instance == null) instance = new CarlsonRoof();
        return instance;
    }
}
