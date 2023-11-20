package com.serezka.scene.entities;

import com.serezka.scene.exceptions.IllegalArgumentException;

public interface Entity {
    String getName();

    void validate() throws IllegalArgumentException;
}
