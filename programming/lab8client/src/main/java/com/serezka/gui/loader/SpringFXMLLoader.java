package com.serezka.gui.loader;

import org.springframework.context.ApplicationContext;
import javafx.fxml.FXMLLoader;

public class SpringFXMLLoader {
    private final ApplicationContext context;

    public SpringFXMLLoader(ApplicationContext context) {
        this.context = context;
    }

    public FXMLLoader getLoader(String url) {
        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory(context::getBean);
        loader.setLocation(getClass().getResource(url));
        return loader;
    }
}
