package programming.lab2.moves.special;

import ru.ifmo.se.pokemon.*;

import java.util.Arrays;

public class ThunderShock extends SpecialMove {
    public ThunderShock() {
        super(Type.ELECTRIC, 40, 100);
    }

    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        if (0.1 < Math.random() ||
                Arrays.stream(pokemon.getTypes()).anyMatch(type -> type == Type.ELECTRIC)) return;
        Effect.paralyze(pokemon);
    }

    @Override
    protected String describe() {
        return super.describe() + " " + getClass().getSimpleName();
    }
}
