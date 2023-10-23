package programming.lab2.moves.special;

import ru.ifmo.se.pokemon.*;

public class Blizzard extends SpecialMove {
    public Blizzard() {
        super(Type.ICE, 110,70);
    }

    @Override
    protected String describe() {
        return super.describe() + " " + getClass().getSimpleName();
    }

    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        if (0.1 < Math.random()) return;
        Effect.freeze(pokemon);
    }
}
