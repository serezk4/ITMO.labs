package programming.lab2;

import programming.lab2.pokemons.*;
import ru.ifmo.se.pokemon.Battle;
import ru.ifmo.se.pokemon.Pokemon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("WARN! Generation used: I to V\n\n");

        // create battle
        Battle battle = new Battle();

        // load and shuffle pokemons
        List<Pokemon> pokemons = new ArrayList<>(List.of(new Fearow(), new Gloom(), new Metapod(), new Quilava(), new Scyther(), new Vileplume()));
        Collections.shuffle(pokemons);

        // print loaded pokemons
        System.out.println(" > POKEMONS LOADED > ");
        pokemons.forEach(pokemon -> System.out.printf(" | [%d] %s %n", pokemon.getLevel(), pokemon.getClass().getSimpleName()));
        System.out.println();

        // add to commands
        for (int i = 0; i < pokemons.size(); i++)
            if (i % 2 == 0) battle.addFoe(pokemons.get(i));
            else battle.addAlly(pokemons.get(i));

        // start battle
        battle.go();
    }
}
