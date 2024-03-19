package com.serezka.server.localization;

import com.serezka.server.authorization.database.model.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * Class for localization
 * Allows to get localized messages
 * @version 1.0
 */
@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Log4j2
public class Localization {
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    @RequiredArgsConstructor @Getter
    public enum Type {
        RU("русский", Locale.of("ru_RU")),
        US("english", Locale.of("en_US"));

        String name;
        Locale locale;

        public static final Type DEFAULT = Type.RU;
    }

    ResourceBundleMessageSource messageSource;

    private Localization() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("lang/lang");
        messageSource.setDefaultLocale(Type.DEFAULT.getLocale());
        messageSource.setDefaultEncoding("UTF-8");

        this.messageSource = messageSource;
    }

    private static Localization instance = null;

    public static Localization getInstance() {
        if (instance == null) instance = new Localization();
        return instance;
    }

    /**
     * Get localized message
     * @param code - message code
     * @param user - user to get localization
     * @return localized message
     */
    public String get(String code, User user) {
        return get(code, user.getLocalization());
    }

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
            return messageSource.getMessage(code, null, Type.DEFAULT.getLocale());
        } catch (NoSuchMessageException e) {
            log.warn(e.getMessage());
            return code;
        }
    }
}
