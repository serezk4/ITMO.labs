package programming.lab2.moves.status;

import ru.ifmo.se.pokemon.StatusMove;
import ru.ifmo.se.pokemon.Type;

// TODO!
public class LightScreen extends StatusMove {
    public LightScreen() {
        super(Type.PSYCHIC,0,0);
    }

    @Override
    protected String describe() {
        return super.describe() + " " + getClass().getSimpleName();
    }
}
