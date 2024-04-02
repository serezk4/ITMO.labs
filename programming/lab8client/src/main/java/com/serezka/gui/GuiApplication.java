package com.serezka.gui;

import com.serezka.Client;
import com.serezka.gui.stage.StageReadyEvent;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class GuiApplication extends Application {
    private static ConfigurableApplicationContext applicationContext;

    @Override
    public void init() throws Exception {
        applicationContext = new SpringApplicationBuilder(Client.class).run();
    }

    @Override
    public void start(Stage primaryStage) {
        applicationContext.publishEvent(new StageReadyEvent(primaryStage));
    }

    @Override
    public void stop() {
        applicationContext.close();
    }
}

