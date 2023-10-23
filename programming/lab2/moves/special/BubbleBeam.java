package programming.lab2.moves.special;

import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.SpecialMove;
import ru.ifmo.se.pokemon.Stat;
import ru.ifmo.se.pokemon.Type;

public class BubbleBeam extends SpecialMove {
    public BubbleBeam() {
        super(Type.WATER, 65, 100);
    }

    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        if (0.332 < Math.random()) return;

        // reduce opponent's speed by one stage (max -6)
        pokemon.setMod(Stat.SPEED, (int) (pokemon.getStat(Stat.SPEED)-1));
    }

    @Override
    protected String describe() {
        return super.describe() + " " + getClass().getSimpleName();
    }
}
