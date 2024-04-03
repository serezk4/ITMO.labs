package com.serezka;

import com.serezka.gui.GuiApplication;
import javafx.application.Application;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Log4j2
public class Client {
//    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
//        /*context = */SpringApplication.run(Client.class, args);
        Application.launch(GuiApplication.class, args);
    }
}
