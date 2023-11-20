package com.serezka.scene.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class IllegalArgumentException extends RuntimeException {
    public IllegalArgumentException(String message) {
        super(message);
    }
}
