package com.serezka;

import com.serezka.scene.MyScene;
import com.serezka.scene.exceptions.EmptyStoryException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws EmptyStoryException, IOException {
        BufferedWriter consoleWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        consoleWriter.append("lab4").append("\n").flush();
        consoleWriter.append(new MyScene().build()).flush();

        consoleWriter.close();
    }
}