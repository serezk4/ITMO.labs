package com.serezka.scene.entities.family.list;

import com.serezka.scene.entities.family.FamilyImpl;
import com.serezka.scene.entities.human.list.Fille;
import com.serezka.scene.entities.human.list.Rulle;
import com.serezka.scene.entities.place.list.House;

import java.util.Collections;
import java.util.List;

public class Svantesoni extends FamilyImpl {
    private Svantesoni() {
        super("Свантесонов", List.of(Fille.getInstance(), Rulle.getInstance()), new House());
    }

    private static Svantesoni instance = null;

    public static Svantesoni getInstance() {
        if (instance == null) instance = new Svantesoni();
        return instance;
    }
}
