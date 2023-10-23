package com.serezka.scene.entities.util;

import com.serezka.scene.entities.human.Human;

import java.util.Arrays;
import java.util.stream.Collectors;

public class HumanUtils {
    public static String toString(Human... humans) {
        final String names = Arrays.stream(humans)
                .map(Human::getName)
                .map(name -> String.join(" ", name, "и", ""))
                .collect(Collectors.joining());
        return names.substring(0, names.length() - 2);
    }
}
