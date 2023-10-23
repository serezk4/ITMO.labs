package programming.lab2.pokemons;

import programming.lab2.moves.MovesLevelsUtil;
import programming.lab2.moves.physical.ShadowPunch;
import programming.lab2.moves.status.LightScreen;
import programming.lab2.moves.status.SandAttack;
import programming.lab2.moves.status.ThunderWave;
import ru.ifmo.se.pokemon.Move;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Scyther extends Pokemon {
    public Scyther() {
        super("Scyther", 0);

        setStats(70,110,80,55,80,105);
        setType(Type.BUG, Type.FLYING);

        final Move[] moves = new Move[]{new LightScreen(), new ThunderWave(), new ShadowPunch(), new SandAttack()};

        setMove(moves);
        setLevel(MovesLevelsUtil.findMinimalLvl(moves));
    }
}
