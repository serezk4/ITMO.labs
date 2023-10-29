package com.serezka.scene.entities.place.list;

public class KidHouse extends House{

    private KidHouse() {}
    private static KidHouse instance = null;

    public static KidHouse getInstance() {
        if (instance == null) instance = new KidHouse();
        return instance;
    }
}
