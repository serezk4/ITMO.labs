package com.serezka.scene;

import com.serezka.Scene;
import com.serezka.scene.entities.action.list.*;
import com.serezka.scene.entities.family.Family;
import com.serezka.scene.entities.family.list.Svantesoni;
import com.serezka.scene.entities.human.Human;
import com.serezka.scene.entities.human.list.*;
import com.serezka.scene.entities.place.Place;
import com.serezka.scene.entities.place.list.CarlsonRoof;
import com.serezka.scene.entities.qualifers.list.Always;
import com.serezka.scene.entities.qualifers.list.Despair;
import com.serezka.scene.entities.qualifers.list.PoorGuy;
import com.serezka.scene.entities.qualifers.list.Scared;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class MyScene extends Scene {
    private final TextBuilder history = TextBuilder.getInstance();

    Human kid = Kid.getInstance();
    Human carlson = Carlson.getInstance();
    Human fille = Fille.getInstance();
    Human rulle = Rulle.getInstance();
    Human sweep = Sweep.getInstance();

    Family svantesoni = Svantesoni.getInstance();

    Place carlsonRoof = CarlsonRoof.getInstance();

    @Override
    public String build() {


        history.add(kid.qualify(new Scared()))
                .add(kid.action(new TakeBreath()))
                .add(kid.qualify(new Despair()))
                .add(carlson.action(new TakeAway()))
                .add(rulle.actionWith(new Notice(), fille))
                .add(kid.action(new ClenchFists()))
                .add(kid.action(new HoldTears()))
                .add(kid.action(new StoppedWorking()))
                .add(kid.action(new Tried()))
                .add(kid.action(new Hear()))
                .add(rulle.action(new Say()));

        history.add(kid.action(new EyesRounded()))
                .add(kid.action(new Mishear()))
                .add(rulle.actionWith(new Think(), fille))
                .add(carlson.action(new Live(svantesoni.getName())))
                .add(carlson.qualify(new Always()))
                .add(carlson.action(new CanHide()))
                .add(carlsonRoof.getName())
                .add(rulle.actionWith(new DidntTrackDownOfHim(), fille))
                .add(sweep.action(new DontClimbOnRoofs()))
                .add(rulle.actionWith(new DontUnderstood(), fille))
                .add(carlson.qualify(new PoorGuy()))
                .add(carlson.action(new CantHide()));

        history.add(fille.action(new KeepSilent()))
                .add(fille.actionWith(new Sit("на скамейка"), fille))
                .add(fille.action(new Look()))
                .add(kid.getName());


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
