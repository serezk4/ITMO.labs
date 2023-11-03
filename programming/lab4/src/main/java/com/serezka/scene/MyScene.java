package com.serezka.scene;

import com.serezka.Scene;
import com.serezka.scene.entities.action.list.*;
import com.serezka.scene.entities.family.list.Svantesoni;
import com.serezka.scene.entities.human.list.*;
import com.serezka.scene.entities.place.list.CarlsonRoof;
import com.serezka.scene.entities.qualifers.list.Always;
import com.serezka.scene.entities.qualifers.list.Despair;
import com.serezka.scene.entities.qualifers.list.PoorGuy;
import com.serezka.scene.entities.qualifers.list.Scared;
import com.serezka.scene.exceptions.EmptyStoryException;

import java.util.Objects;

public class MyScene extends Scene {
    private final TextBuilder history = TextBuilder.getInstance();

    @Override
    public String build() throws EmptyStoryException {
        history.add(Kid.getInstance().qualify(new Scared()))
                .add(Kid.getInstance().action(new TakeBreath()))
                .add(Kid.getInstance().qualify(new Despair()))
                .add(Carlson.getInstance().action(new TakeAway()))
                .add(Rulle.getInstance().actionWith(new Notice(), Fille.getInstance()))
                .add(Kid.getInstance().action(new ClenchFists()))
                .add(Kid.getInstance().action(new HoldTears()))
                .add(Kid.getInstance().action(new StoppedWorking()))
                .add(Kid.getInstance().action(new Tried()))
                .add(Kid.getInstance().action(new Hear()))
                .add(Rulle.getInstance().action(new Say()));

        history.add(Kid.getInstance().action(new EyesRounded()))
                .add(Kid.getInstance().action(new Mishear()))
                .add(Rulle.getInstance().actionWith(new Think(), Fille.getInstance()))
                .add(Carlson.getInstance().action(new Live(Svantesoni.getInstance().getName())))
                .add(Carlson.getInstance().qualify(new Always()))
                .add(Carlson.getInstance().action(new CanHide()))
                .add(CarlsonRoof.getInstance().getName())
                .add(Rulle.getInstance().actionWith(new DidntTrackDownOfHim(), Fille.getInstance()))
                .add(Sweep.getInstance().action(new DontClimbOnRoofs()))
                .add(Rulle.getInstance().actionWith(new DontUnderstood(), Fille.getInstance()))
                .add(Carlson.getInstance().qualify(new PoorGuy()))
                .add(Carlson.getInstance().action(new CantHide()));

        history.add(Fille.getInstance().action(new KeepSilent()))
                .add(Fille.getInstance().actionWith(new Sit("на скамейка"), Fille.getInstance()))
                .add(Fille.getInstance().action(new Look()))
                .add(Kid.getInstance().getName());


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
