package com.serezka.lab.core.database.model;

import com.github.f4b6a3.uuid.UuidCreator;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity @Table(name = "tokens")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String token;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    User user;

    public Token(User user) {
        this.token = UuidCreator.getRandomBased().toString() + UuidCreator.getTimeBased().toString();
        this.user = user;
    }
}
