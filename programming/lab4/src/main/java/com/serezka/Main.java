package com.serezka;

import com.serezka.scene.MyScene;
import com.serezka.scene.exceptions.EmptyStoryException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws EmptyStoryException, IOException {
        System.out.println(new MyScene().build());
    }
}