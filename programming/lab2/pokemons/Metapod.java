package programming.lab2.pokemons;

import programming.lab2.moves.MovesLevelsUtil;
import programming.lab2.moves.physical.ShadowPunch;
import programming.lab2.moves.status.LightScreen;
import programming.lab2.moves.status.ThunderWave;
import ru.ifmo.se.pokemon.Move;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Metapod extends Pokemon {
    public Metapod() {
        super("Metapod", 0);

        setStats(50,20,55,25,25,30);
        setType(Type.BUG);

        final Move[] moves = new Move[]{new LightScreen(), new ThunderWave(), new ShadowPunch()};

        setMove(moves);
        setLevel(MovesLevelsUtil.findMinimalLvl(moves));
    }
}
