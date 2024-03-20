package com.serezka.gui;

import com.serezka.Main;
import com.serezka.configuration.RuntimeConfiguration;
import com.serezka.net.RestClient;
import com.serezka.net.methods.authorization.AuthenticationResponse;
import com.serezka.net.methods.authorization.Login;
import com.serezka.net.methods.authorization.Register;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import lombok.SneakyThrows;

import java.io.IOException;

public class LoginController {
    @FXML // fx:id="authorize"
    private Button authorize; // Value injected by FXMLLoader

    @FXML // fx:id="error_text"
    private Text error_text; // Value injected by FXMLLoader

    @FXML // fx:id="login"
    private TextField login; // Value injected by FXMLLoader

    @FXML // fx:id="password"
    private PasswordField password; // Value injected by FXMLLoader

    @FXML
    void login(ActionEvent event) {
        sendLoginRequest(login.getText(), password.getText());
    }

    @SneakyThrows
    private void sendLoginRequest(String username, String password) {

        AuthenticationResponse authenticationResponse = new Login(username, password).execute();

        if (authenticationResponse.isError()) {
            error_text.setText(authenticationResponse.getToken());
            return;
        }

        RuntimeConfiguration.setJwtToken(authenticationResponse.getToken());
        error_text.setText("");

        try {
            Main.showWorkspaceScene();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void handleLogin(ActionEvent actionEvent) {

    }

    public void handleRegister(ActionEvent actionEvent) {

    }
}


