package programming.lab2.moves.status;

import ru.ifmo.se.pokemon.*;

public class Supersonic extends StatusMove {
    public Supersonic() {
        super(Type.NORMAL, 0, 55);
    }

    @Override
    protected String describe() {
        return super.describe() + " " + getClass().getSimpleName();
    }

    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        Effect.confuse(pokemon);
    }
}
