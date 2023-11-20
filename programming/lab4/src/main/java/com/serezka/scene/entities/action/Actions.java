package com.serezka.scene.entities.action;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

public class Actions {
    public static class AlmostHear extends ActionImpl {
        public AlmostHear() {
            super("едва расслышать");
        }
    }


    public static class CanHide extends ActionImpl {
        public CanHide() {
            super("может спрятаться");
        }
    }


    public static class CantHide extends ActionImpl {
        public CantHide() {
            super("не умел прятаться");
        }
    }


    public static class CaughtOn extends ActionImpl {
        public CaughtOn() {
            super("спохватиться");
        }
    }


    public static class ClenchFists extends ActionImpl {
        public ClenchFists() {
            super("сжать кулаки");
        }
    }


    public static class DidntTrackDownOfHim extends ActionImpl {
        public DidntTrackDownOfHim() {
            super("его не выследили");
        }
    }


    public static class DontClimbOnRoofs extends ActionImpl {
        public DontClimbOnRoofs() {
            super("лазает по крышам");
        }
    }


    public static class DontUnderstood extends ActionImpl {
        public DontUnderstood() {
            super("не пронюхали");
        }
    }


    public static class EyesRounded extends ActionImpl {
        public EyesRounded() {
            super("глаза округлились");
        }
    }


    public static class HaveTo extends ActionImpl {
        public HaveTo() {
            super("придется");
        }
    }


    public static class Hear extends ActionImpl {
        public Hear() {
            super("услышать");
        }
    }


    public static class HoldTears extends ActionImpl {
        public HoldTears() {
            super("сдержать слезы");
        }
    }


    public static class KeepSilent extends ActionImpl {
        public KeepSilent() {
            super("молчать");
        }
    }

    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Live extends ActionImpl {
        String with = "";

        public Live() {
            super("живет");
        }

        public Live(String with) {
            this();
            this.with = "с " + with;
        }

        @Override
        public String getName() {
            return super.getName() + with;
        }
    }


    public static class Look extends ActionImpl {
        public Look() {
            super("глядеть");
        }
    }


    public static class Mean extends ActionImpl {
        public Mean() {
            super("ведь значит");
        }
    }


    public static class Mishear extends ActionImpl {
        public Mishear() {
            super("ослышался");
        }
    }


    public static class Notice extends ActionImpl {
        public Notice() {
            super("заметить домик");
        }
    }


    public static class Say extends ActionImpl {
        public Say() {
            super("сказать");
        }
    }

    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Sit extends ActionImpl {
        String where = "";

        public Sit() {
            super("сидеть");
        }

        public Sit(String where) {
            this();
            this.where = where;
        }

        @Override
        public String getName() {
            return super.getName() + where;
        }
    }


    public static class StayThere extends ActionImpl {
        public StayThere() {
            super("быть там");
        }
    }


    public static class StoppedWorking extends ActionImpl {
        public StoppedWorking() {
            super("перестало удаваться");
        }
    }


    public static class TakeAway extends ActionImpl {
        public TakeAway() {
            super("смывать удочки");
        }
    }


    public static class TakeBreath extends ActionImpl {
        public TakeBreath() {
            super("перевести дух");
        }
    }


    public static class Think extends ActionImpl {
        public Think() {
            super("думают");
        }
    }


    public static class Tried extends ActionImpl {
        public Tried() {
            super("стараться");
        }
    }
}
