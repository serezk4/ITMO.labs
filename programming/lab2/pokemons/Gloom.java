package programming.lab2.pokemons;

import programming.lab2.moves.MovesLevelsUtil;
import programming.lab2.moves.physical.FirePunch;
import programming.lab2.moves.physical.ShadowPunch;
import programming.lab2.moves.status.ThunderWave;
import ru.ifmo.se.pokemon.Move;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Gloom extends Pokemon {
    public Gloom() {
        super("Gloom", 0);

        setStats(60,65,70,85,75,40);
        setType(Type.GRASS, Type.POISON);

        final Move[] moves = new Move[]{new ShadowPunch(), new ThunderWave(), new FirePunch()};

        setMove(moves);
        setLevel(MovesLevelsUtil.findMinimalLvl(moves));
    }
}
