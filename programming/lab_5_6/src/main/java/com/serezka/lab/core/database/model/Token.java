package com.serezka.lab.core.database.model;

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

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    User user;

    public Token(String token, User user) {
        this.token = token;
        this.user = user;
    }
}
