package com.serezka.server.database.model.collection;

import com.serezka.server.database.model.authorization.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Entity
@Table(name = "flats")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flat implements Comparable<Flat> {
    /**
     * Поле не может быть null
     * Значение поля должно быть больше 0
     * Значение этого поля должно быть уникальным
     * Значение этого поля должно генерироваться автоматически
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    User userId;

    /**
     * Поле не может быть null
     * Строка не может быть пустой
     */
    @Column(nullable = false)
    String name;

    /**
     * Поле не может быть null
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coordinates", referencedColumnName = "id")
    Coordinates coordinates;

    /**
     * Поле не может быть null
     * Значение этого поля должно генерироваться автоматически
     */
    @Builder.Default
    @Column(name = "creation_date")
    Calendar creationDate = GregorianCalendar.from(ZonedDateTime.now());

    /**
     * Максимальное значение поля: 734
     * Значение поля должно быть больше 0
     */
    Long area;

    /**
     * Поле не может быть null
     * Значение поля должно быть больше 0
     */
    @Column(name = "number_of_rooms")
    Integer numberOfRooms;

    /**
     * Значение поля должно быть больше 0
     */
    @Column(name = "living_space")
    Double livingSpace;

    boolean furniture;

    /**
     * Поле может быть null
     */
    Transport transport;

    /**
     * Поле не может быть null
     */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "houses", referencedColumnName = "id", nullable = false)
    House house;

    @Override
    public int compareTo(Flat o) {
        return this.livingSpace.compareTo(o.livingSpace) + (this.furniture && o.furniture ? 1 : 0);
    }
}

