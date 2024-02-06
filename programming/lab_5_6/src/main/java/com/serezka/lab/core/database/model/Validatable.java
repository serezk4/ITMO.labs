package com.serezka.lab.core.database.model;

import com.serezka.lab.core.database.model.exceptions.RequirementsException;

public interface Validatable {
    boolean validate() throws RequirementsException;
}
