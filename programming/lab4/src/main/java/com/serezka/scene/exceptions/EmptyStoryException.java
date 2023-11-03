package com.serezka.scene.exceptions;

public class EmptyStoryException extends Exception {
    public EmptyStoryException() {
        super("the story must be non-empty!");
    }
}
