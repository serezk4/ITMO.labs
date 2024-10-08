package com.serezka.server.authorization.database.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.serezka.server.localization.Localization;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity @Table(name = "users")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@ToString
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "mail", nullable = false, unique = true)
    private String mail;

    @Column(name = "registered", updatable = false, nullable = false)
    private final ZonedDateTime registered = ZonedDateTime.now(ZoneId.systemDefault());

    @Builder.Default
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Role role = Role.DEFAULT;

    @Builder.Default
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    Localization.Type localization = Localization.Type.DEFAULT;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public enum Role {
        USER,
        ADMIN;

        public static final Role DEFAULT = USER;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, mail, registered, role, localization);
    }
}
