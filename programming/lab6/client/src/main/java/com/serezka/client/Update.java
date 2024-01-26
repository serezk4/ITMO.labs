package com.serezka.client;

import com.serezka.client.chat.object.Product;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@FieldDefaults(level = AccessLevel.PUBLIC)
@Getter @Setter @RequiredArgsConstructor  @Data
@AllArgsConstructor
public class Update implements Serializable {
    Product product;
    private String message = "123";
}
