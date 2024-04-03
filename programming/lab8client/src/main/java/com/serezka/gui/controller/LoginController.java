package com.serezka.gui.controller;

import com.serezka.configuration.RuntimeConfiguration;
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

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginController {
    private final AuthenticationRestClient authenticationRestClient;
    private final StageHandler stageHandler;

    @FXML Button authorize;
    @FXML Text errorText;
    @FXML TextField login;
    @FXML PasswordField password;
    @FXML Button register;

    @FXML
    void login(ActionEvent event) {
        sendLoginRequest(login.getText(), password.getText());
    }

    @SneakyThrows
    private void sendLoginRequest(String username, String password) {
        try {
            AuthenticationResponse authenticationResponse = authenticationRestClient.login(username, password);

            if (authenticationResponse.isError()) {
                errorText.setText(authenticationResponse.getToken());
                return;
            }

            RuntimeConfiguration.setJwtToken(authenticationResponse.getToken());
            errorText.setText("");

            stageHandler.showWorkspaceScene();
        } catch (Exception e) {
            errorText.setText("Server is not available");
        }
    }

    public void handleLogin(ActionEvent actionEvent) {
        
    }

    @FXML
    void handleRegister(ActionEvent event) {
        try {
            stageHandler.showRegisterScene();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}


