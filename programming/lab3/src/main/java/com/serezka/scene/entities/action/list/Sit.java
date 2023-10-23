package com.serezka.scene.entities.action.list;

import com.serezka.scene.entities.action.ActionImpl;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Sit extends ActionImpl {
    String where = "";

    public Sit() {
        super("сидеть");
    }

    public Sit(String where) {
        this();
        this.where=where;
    }

    @Override
    public String getName() {
        return super.getName() + where;
    }
}
