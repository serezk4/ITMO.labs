package com.serezka.scene.entities.family;

import com.serezka.scene.entities.human.Humans;
import com.serezka.scene.entities.place.Places;

import java.util.List;

public class Families {
    public static class Svantesoni extends FamilyImpl {
        private Svantesoni() {
            super("Свантесонов", List.of(Humans.Fille.getInstance(), Humans.Rulle.getInstance()), new Places.House());
        }

        private static Svantesoni instance = null;

        public static Svantesoni getInstance() {
            if (instance == null) instance = new Svantesoni();
            return instance;
        }
    }

}
