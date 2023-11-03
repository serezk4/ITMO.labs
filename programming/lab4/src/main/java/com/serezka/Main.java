package com.serezka;

import com.serezka.scene.MyScene;
import com.serezka.scene.exceptions.EmptyStoryException;

public class Main {
    public static void main(String[] args) throws EmptyStoryException {
        System.out.println(new MyScene().build());
    }
}