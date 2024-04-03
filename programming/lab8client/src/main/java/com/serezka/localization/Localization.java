package com.serezka.localization;

import com.serezka.configuration.RuntimeConfiguration;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * Class for localization
 * Allows to get localized messages
 * @version 1.0
 */
@Component @Scope("singleton")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Log4j2
public class Localization {
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    @RequiredArgsConstructor @Getter
    public enum Type {
        RU("RU", Locale.of("ru")),
        US("EN", Locale.of("us")),
        FR("FR", Locale.of("fr")),
        NO("NO", Locale.of("no"));

        String name;
        Locale locale;

        public static final Type DEFAULT = Type.RU;

        public static Type fromString(String name) {
            for (Type type : Type.values())
                if (type.getName().equalsIgnoreCase(name)) return type;
            return DEFAULT;
        }
    }

    ResourceBundleMessageSource messageSource;

    /**
     * Get localized message
     * @param code - message code
     * @param localization - localization type
     * @return localized message
     */
    public String get(String code, Type localization) {
        if (localization == null) return get(code);

        try {
            return messageSource.getMessage(code, null, localization.getLocale());
        } catch (NoSuchMessageException e) {
            log.warn(e.getMessage());
            return code;
        }
    }

    /**
     * Get localized message
     * @param code - message code
     * @return localized message with default locale
     */
    public String get(String code) {
        try {
            return messageSource.getMessage(code, new Object[0], RuntimeConfiguration.getLocalizationType().getLocale());
        } catch (NoSuchMessageException e) {
            log.warn(e.getMessage());
            return code;
        }
    }
}
