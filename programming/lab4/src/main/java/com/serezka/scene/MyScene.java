package com.serezka.scene;

import com.serezka.Scene;
import com.serezka.scene.entities.action.Actions;
import com.serezka.scene.entities.family.Families;
import com.serezka.scene.entities.family.Family;
import com.serezka.scene.entities.family.FamilyImpl;
import com.serezka.scene.entities.human.Human;
import com.serezka.scene.entities.human.Humans;
import com.serezka.scene.entities.place.Place;
import com.serezka.scene.entities.place.Places;
import com.serezka.scene.entities.qualifers.Qualifers;
import com.serezka.scene.exceptions.EmptyStoryException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Objects;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MyScene extends Scene {
    TextBuilder history = TextBuilder.getInstance();

    // init
    Human kid = Humans.Kid.getInstance();
    Human carlson = Humans.Carlson.getInstance();
    Human fille = Humans.Fille.getInstance();
    Human rulle = Humans.Rulle.getInstance();
    Human sweep = Humans.Sweep.getInstance();

    Place carlsonRoof = Places.CarlsonRoof.getInstance();

    Family svantesoni = new FamilyImpl("Свантесоны", List.of(Humans.Fille.getInstance(), Humans.Rulle.getInstance()), new Places.House()) {
        @Override
        public String getName() {
            return "Свантесонов";
        }
    };

    @Override
    public String build() throws EmptyStoryException {
        history.add(kid.qualify(new Qualifers.Scared()))
                .add(kid.action(new Actions.TakeBreath()))
                .add(kid.qualify(new Qualifers.Despair()))
                .add(carlson.action(new Actions.TakeAway()))
                .add(rulle.actionWith(new Actions.Notice(), fille))
                .add(kid.action(new Actions.ClenchFists()))
                .add(kid.action(new Actions.HoldTears()))
                .add(kid.action(new Actions.StoppedWorking()))
                .add(kid.action(new Actions.Tried()))
                .add(kid.action(new Actions.Hear()))
                .add(rulle.action(new Actions.Say()));

        history.add(kid.action(new Actions.EyesRounded()))
                .add(kid.action(new Actions.Mishear()))
                .add(rulle.actionWith(new Actions.Think(), fille))
                .add(carlson.action(new Actions.Live(svantesoni.getName())))
                .add(carlson.qualify(new Qualifers.Always()))
                .add(carlson.action(new Actions.CanHide()))
                .add(carlsonRoof.getName())
                .add(rulle.actionWith(new Actions.DidntTrackDownOfHim(), fille))
                .add(sweep.action(new Actions.DontClimbOnRoofs()))
                .add(rulle.actionWith(new Actions.DontUnderstood(), fille))
                .add(carlson.qualify(new Qualifers.PoorGuy()))
                .add(carlson.action(new Actions.CantHide()));

        history.add(fille.action(new Actions.KeepSilent()))
                .add(fille.actionWith(new Actions.Sit("на скамейка"), fille))
                .add(fille.action(new Actions.Look()))
                .add(kid.getName());


        if (history.getResult().isBlank()) throw new EmptyStoryException();

        return history.getResult();
    }

    @Override
    public String toString() {
        return "Сцена {" +
                "history=" + history +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyScene myScene = (MyScene) o;
        return Objects.equals(history, myScene.history);
    }

    @Override
    public int hashCode() {
        return Objects.hash(history);
    }
}
