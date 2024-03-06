package com.serezka.objects.exceptions;

public class RequirementsException extends RuntimeException {
    public RequirementsException(String field, String requirements) {
        super(String.format("Поле %s должно быть %s", field, requirements));
    }
}
