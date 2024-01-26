package com.serezka.server.handler.handler;

import com.serezka.server.handler.object.Product;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter @Setter @RequiredArgsConstructor @AllArgsConstructor @Data
public class Update implements Serializable {
    Product product;
    private String message;

}
