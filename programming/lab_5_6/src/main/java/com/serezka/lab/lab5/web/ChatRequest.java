package com.serezka.lab.lab5.web;

import com.serezka.lab.core.database.model.Flat;

import java.util.Set;

public class ChatRequest {
    private String message;
    private Set<Flat> temporaryCollection;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Set<Flat> getTemporaryCollection() {
        return temporaryCollection;
    }

    public void setTemporaryCollection(Set<Flat> temporaryCollection) {
        this.temporaryCollection = temporaryCollection;
    }
}