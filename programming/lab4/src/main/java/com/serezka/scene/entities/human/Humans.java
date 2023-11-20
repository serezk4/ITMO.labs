package com.serezka.scene.entities.human;

import com.serezka.scene.entities.place.Places;

public class Humans {
    public static class Carlson extends HumanImpl {
        private Carlson() {
            super("Карлсон", 30, Places.CarlsonRoof.getInstance(), Gender.MALE);
        }

        private static Carlson instance = null;

        public static Carlson getInstance() {
            if (instance == null) instance = new Carlson();
            return instance;
        }
    }

    public static class Fille extends HumanImpl {
        private Fille() {
            super("Филле", 9, Places.FilleAndRulleHouse.getInstance(), Gender.MALE);
        }

        private static Fille instance = null;

        public static Fille getInstance() {
            if (instance == null) instance = new Fille();
            return instance;
        }
    }

    public static class Kid extends HumanImpl {
        private Kid() {
            super("Малыш", 10, Places.KidHouse.getInstance(), Gender.MALE);
        }

        private static Kid instance = null;

        public static Kid getInstance() {
            if (instance == null) instance = new Kid();
            return instance;
        }
    }

    public static class Rulle extends HumanImpl {
        private Rulle() {
            super("Рулле", 9, Places.FilleAndRulleHouse.getInstance(), Gender.MALE);
        }

        private static Rulle instance = null;

        public static Rulle getInstance() {
            if (instance == null) instance = new Rulle();
            return instance;
        }
    }

    public static class Sweep extends HumanImpl {
        private Sweep() {
            super("трубочиста", 50, null, Gender.MALE);
        }

        private static Sweep instance = null;

        public static Sweep getInstance() {
            if (instance == null) instance = new Sweep();
            return instance;
        }
    }
}
