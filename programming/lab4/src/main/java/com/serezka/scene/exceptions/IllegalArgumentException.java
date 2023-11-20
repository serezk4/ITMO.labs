package com.serezka.scene.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class IllegalArgumentException extends Error {
    public IllegalArgumentException(String message) {
        super(message);
    }
}
