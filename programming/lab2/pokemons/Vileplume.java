package programming.lab2.pokemons;

import programming.lab2.moves.MovesLevelsUtil;
import programming.lab2.moves.physical.FirePunch;
import programming.lab2.moves.physical.ShadowPunch;
import programming.lab2.moves.special.Blizzard;
import programming.lab2.moves.status.ThunderWave;
import ru.ifmo.se.pokemon.Move;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Vileplume extends Pokemon {
    public Vileplume() {
        super("Vileplume", 0);

        setStats(75,80,85,110,90,50);
        setType(Type.GRASS, Type.POISON);

        final Move[] moves = new Move[]{new ShadowPunch(), new ThunderWave(), new FirePunch(), new Blizzard()};

        setMove(moves);
        setLevel(MovesLevelsUtil.findMinimalLvl(moves));
    }
}
