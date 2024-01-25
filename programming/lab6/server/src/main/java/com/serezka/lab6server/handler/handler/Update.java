package com.serezka.lab6server.handler.handler;

import com.serezka.lab6server.handler.object.Product;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

import java.io.Serializable;

@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Getter @Setter @RequiredArgsConstructor @AllArgsConstructor
public class Update implements Serializable {
    @NonFinal Product product;
    private String operation;
}
