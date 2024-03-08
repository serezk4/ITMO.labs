package com.serezka.server.database.model.authorization;

import com.serezka.server.localization.Localization;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.proxy.HibernateProxy;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity @Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Getter @Setter @ToString
@RequiredArgsConstructor
@AllArgsConstructor @NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String username;

    @Column(nullable = false)
    String password;

    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "roles_id", referencedColumnName = "id")
    List<Role> roles = Collections.emptyList();

    @Builder.Default
    @NonNull
    @Column(nullable = false)
    Localization.Type localization = Localization.Type.DEFAULT;

    // other properties
    @Builder.Default
    @Column(name = "is_locked", nullable = false)
    boolean isLocked = false;

    public User(String username, String password, List<Role> roles, @NonNull Localization.Type localization) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.localization = localization;
    }

    public User(String username, String password, @NonNull Localization.Type localization) {
        this.username = username;
        this.password = password;
        this.localization = localization;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return isLocked == user.isLocked && Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(roles, user.roles) && localization == user.localization;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, roles, localization, isLocked);
    }
}
