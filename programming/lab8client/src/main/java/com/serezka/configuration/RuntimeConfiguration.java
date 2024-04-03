package com.serezka.configuration;

import com.serezka.localization.Localization;
import lombok.Getter;
import lombok.Setter;

public class RuntimeConfiguration {
    @Getter @Setter
    private static volatile String jwtToken;

    @Getter @Setter
    private static volatile Localization.Type localizationType = Localization.Type.DEFAULT;
}
