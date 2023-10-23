package programming.lab2.pokemons;

import programming.lab2.moves.MovesLevelsUtil;
import programming.lab2.moves.special.Blizzard;
import programming.lab2.moves.special.BubbleBeam;
import programming.lab2.moves.special.ThunderShock;
import programming.lab2.moves.status.Supersonic;
import ru.ifmo.se.pokemon.Move;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Fearow extends Pokemon {
    public Fearow() {
        super("Fearow", 0);

        setStats(40, 60, 30, 31, 31, 70);
        setType(Type.NORMAL, Type.FLYING);

        final Move[] moves = new Move[]{new ThunderShock(), new Supersonic(), new BubbleBeam(), new Blizzard()};

        setMove(moves);
        setLevel(MovesLevelsUtil.findMinimalLvl(moves));
    }

    public Fearow(int d) {
        this();
    }
}
