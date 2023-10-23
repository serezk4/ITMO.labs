package programming.lab2.pokemons;

import programming.lab2.moves.MovesLevelsUtil;
import programming.lab2.moves.physical.ShadowPunch;
import programming.lab2.moves.status.ThunderWave;
import ru.ifmo.se.pokemon.Move;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Quilava extends Pokemon {
    public Quilava() {
        super("Quilava", 0);

        setStats(58,64,58,80,65,80);
        setType(Type.FIRE);

        final Move[] moves = new Move[]{new ShadowPunch(), new ThunderWave()};

        setMove(moves);
        setLevel(MovesLevelsUtil.findMinimalLvl(moves));

    }
}
