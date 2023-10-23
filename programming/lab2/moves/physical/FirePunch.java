package programming.lab2.moves.physical;

import ru.ifmo.se.pokemon.Effect;
import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class FirePunch extends PhysicalMove {
    public FirePunch() {
        super(Type.FIRE, 75, 100);
    }

    @Override
    protected String describe() {
        return super.describe() + " " + getClass().getSimpleName();
    }

    @Override
    protected void applyOppEffects(Pokemon pokemon) {
        // Fire type Pokémon, those with the ability Water Veil or those behind a Substitute cannot be burned.
        if (0.1 < Math.random()) return;
        Effect.burn(pokemon);
    }
}
