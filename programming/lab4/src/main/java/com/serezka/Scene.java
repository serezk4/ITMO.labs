package com.serezka;

import com.serezka.scene.exceptions.EmptyStoryException;

import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class Scene {
    public abstract String build() throws EmptyStoryException, IOException;
}
