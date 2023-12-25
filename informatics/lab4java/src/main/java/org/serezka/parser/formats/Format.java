package org.serezka.parser.formats;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public abstract class Format {
    final String raw;

    public Format(String raw) {
        this.raw = raw;
    }

    public String get() {
        return this.raw;
    };
}
