package com.serezka.lab.core.database.model;

import com.serezka.lab.core.v2.localization.Localization;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity @Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data
@AllArgsConstructor @NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String username;
    String hashPassword;

    @Builder.Default
    @NonNull
    Localization.Type localization = Localization.Type.DEFAULT;

    public User(String username, String hashPassword) {
        this.username = username;
        this.hashPassword = hashPassword;
    }
}
