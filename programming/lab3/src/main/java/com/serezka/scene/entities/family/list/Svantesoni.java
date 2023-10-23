package com.serezka.scene.entities.family.list;

import com.serezka.scene.entities.family.FamilyImpl;
import com.serezka.scene.entities.place.list.House;

import java.util.Collections;

public class Svantesoni extends FamilyImpl {
    private Svantesoni() {
        super("Свантесонов", Collections.emptyList(), new House());
    }

    private static Svantesoni instance = null;

    public static Svantesoni getInstance() {
        if (instance == null) instance = new Svantesoni();
        return instance;
    }
}
