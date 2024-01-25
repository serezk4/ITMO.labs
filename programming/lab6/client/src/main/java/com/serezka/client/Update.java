package com.serezka.client;

import com.serezka.client.object.Product;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

import java.io.Serializable;

@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Getter @Setter @RequiredArgsConstructor @AllArgsConstructor
public class Update implements Serializable {
    @NonFinal
    Product product;
    private String message;
}
