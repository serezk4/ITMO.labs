package com.serezka.server.controller.object.exceptions;

public class RequirementsException extends RuntimeException {
    public RequirementsException(String field, String requirements) {
        super(String.format("Поле %s должно быть %s", field, requirements));
    }
}
