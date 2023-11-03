package com.serezka;

import com.serezka.scene.exceptions.EmptyStoryException;

public abstract class Scene {
    public abstract String build() throws EmptyStoryException;
}
