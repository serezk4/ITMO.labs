package com.serezka.scene.entities.action;

import com.serezka.scene.entities.qualifers.Qualifer;
import com.serezka.scene.entities.human.Human;
import com.serezka.scene.entities.util.HumanUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class ActionImpl implements Action {
    String action;

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
