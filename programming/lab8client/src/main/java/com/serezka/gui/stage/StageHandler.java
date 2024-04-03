package com.serezka.gui.stage;

import com.serezka.gui.loader.SpringFXMLLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component @Scope("singleton")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Log4j2
public class StageHandler {
    @NonFinal @Setter
    Stage primaryStage;
    ApplicationContext applicationContext;

    public StageHandler(ApplicationContext applicationContext) {
        showLoginScene();
        this.applicationContext = applicationContext;
    }

    public void showRegisterScene() {
        try {
            SpringFXMLLoader fxmlLoader = new SpringFXMLLoader(applicationContext);
            FXMLLoader loader = fxmlLoader.getLoader(("/register.fxml"));
            Parent root = loader.load();
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Register");
            primaryStage.show();
        } catch (Exception ex) {
            log.warn(ex.getMessage());
        }
    }

    public void showLoginScene() {
        try {
            SpringFXMLLoader fxmlLoader = new SpringFXMLLoader(applicationContext);
            FXMLLoader loader = fxmlLoader.getLoader("/login_v1.fxml");
            Parent root = loader.load();
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Login");
            primaryStage.show();
        } catch (Exception ex) {
            log.warn(ex.getMessage());
        }
    }

    public void showWorkspaceScene() {
        try {
            SpringFXMLLoader fxmlLoader = new SpringFXMLLoader(applicationContext);
            FXMLLoader loader = fxmlLoader.getLoader(("/workspace.fxml"));
            Parent root = loader.load();
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle("Workspace");
            primaryStage.show();
        } catch (Exception ex) {
            log.warn(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
