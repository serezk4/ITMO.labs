package programming.lab2.moves;

import programming.lab2.moves.physical.FirePunch;
import programming.lab2.moves.physical.ShadowPunch;
import programming.lab2.moves.special.Blizzard;
import programming.lab2.moves.special.BubbleBeam;
import programming.lab2.moves.special.ThunderShock;
import programming.lab2.moves.status.LightScreen;
import programming.lab2.moves.status.SandAttack;
import programming.lab2.moves.status.Supersonic;
import programming.lab2.moves.status.ThunderWave;
import ru.ifmo.se.pokemon.Move;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MovesLevelsUtil extends Blizzard {
    private static final Map<Class, Integer> map = new HashMap<>(Map.of(
            FirePunch.class, 15,
            ShadowPunch.class, 20,
            Blizzard.class, 5,
            BubbleBeam.class, 20,
            ThunderShock.class, 20,
            LightScreen.class, 30,
            SandAttack.class, 15,
            Supersonic.class, 20,
            ThunderWave.class, 20));

    public static int findMinimalLvl(Move[] moves) {
        return Arrays.stream(moves).map(Object::getClass).mapToInt(map::get).max().orElse(-1);
    }
}
