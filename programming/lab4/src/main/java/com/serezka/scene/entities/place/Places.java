package com.serezka.scene.entities.place;

public class Places {
    public static class CarlsonRoof extends Roof {
        public CarlsonRoof() {
            super("карлсона");
        }

        private static CarlsonRoof instance = null;

        public static CarlsonRoof getInstance() {
            if (instance == null) instance = new CarlsonRoof();
            return instance;
        }
    }


    public static class FilleAndRulleHouse extends House {
        public FilleAndRulleHouse() {
            super("филле и рулле");
        }

        private static FilleAndRulleHouse instance = null;

        public static FilleAndRulleHouse getInstance() {
            if (instance == null) instance = new FilleAndRulleHouse();
            return instance;
        }
    }


    public static class House extends PlaceImpl {
        public House(String owner) {
            super(String.join(" ", "дом", owner));
        }

        public House() {
            super("дом");
        }
    }


    public static class KidHouse extends House {
        public KidHouse() {
            super("малыша");
        }

        private static KidHouse instance = null;

        public static KidHouse getInstance() {
            if (instance == null) instance = new KidHouse();
            return instance;
        }
    }


    public static class Roof extends PlaceImpl {
        public Roof(String owner) {
            super(String.join(" ", "домик на крыше", owner));
        }

        public Roof() {
            super("домик на крыше");
        }
    }
}
