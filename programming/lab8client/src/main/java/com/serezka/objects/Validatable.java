package com.serezka.objects;


import com.serezka.objects.exceptions.RequirementsException;

public interface Validatable {
    boolean validate() throws RequirementsException;
}
