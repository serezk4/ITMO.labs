package com.serezka.scene.entities.action;

import com.serezka.scene.exceptions.IllegalArgumentException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ActionImpl implements Action {
    String action;

    public ActionImpl(String action) {
        if (action == null || action.isBlank()) throw new IllegalArgumentException();

        this.action = action;
    }

    @Override
    public String getName() {
        return this.action;
    }

    @Override
    public String toString() {
        return "Действие {" +
                "action='" + action + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActionImpl action1 = (ActionImpl) o;
        return Objects.equals(action, action1.action);
    }

    @Override
    public int hashCode() {
        return action.hashCode();
    }
}
