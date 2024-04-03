package com.serezka.gui.controller;

import com.serezka.gui.stage.StageHandler;
import com.serezka.net.authorization.AuthenticationResponse;
import com.serezka.net.authorization.AuthenticationRestClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterController {
    private final AuthenticationRestClient authenticationRestClient;
    private final StageHandler stageHandler;

    @FXML ResourceBundle resources;
    @FXML URL location;
    @FXML Button authorize1;
    @FXML TextField email;
    @FXML Text error_text;
    @FXML TextField login;
    @FXML PasswordField password;
    @FXML Button register;

    @SneakyThrows
    @FXML
    void backToLogin(ActionEvent event) {
        stageHandler.showLoginScene();
    }

    @FXML
    void handleRegister(ActionEvent event) {
        String login = this.login.getText();
        String password = this.password.getText();
        String email = this.email.getText();

        if (login.isBlank() || password.isBlank() || email.isBlank()) {
            error_text.setText("Fill all fields");
            return;
        }

        AuthenticationResponse response = authenticationRestClient.register(email, login, password);

        if (response.isError()) {
            error_text.setText(response.getToken());
            return;
        }

        error_text.setText("");

        stageHandler.showLoginScene();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert authorize1 != null : "fx:id=\"authorize1\" was not injected: check your FXML file 'register.fxml'.";
        assert email != null : "fx:id=\"email\" was not injected: check your FXML file 'register.fxml'.";
        assert error_text != null : "fx:id=\"error_text\" was not injected: check your FXML file 'register.fxml'.";
        assert login != null : "fx:id=\"login\" was not injected: check your FXML file 'register.fxml'.";
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'register.fxml'.";
        assert register != null : "fx:id=\"register\" was not injected: check your FXML file 'register.fxml'.";
    }

}

