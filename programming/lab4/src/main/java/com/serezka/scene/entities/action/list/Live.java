package com.serezka.scene.entities.action.list;

import com.serezka.scene.entities.action.ActionImpl;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Live extends ActionImpl {
    String with = "";

    public Live() {
        super("живет");
    }

    public Live(String with) {
        this();
        this.with = "с " + with;
    }

    @Override
    public String getName() {
        return super.getName() + with;
    }
}
