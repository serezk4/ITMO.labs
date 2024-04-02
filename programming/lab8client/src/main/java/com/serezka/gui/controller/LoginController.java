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
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginController {
    private final AuthenticationRestClient authenticationRestClient;
    private final StageHandler stageHandler;

    @FXML // fx:id="authorize"
    private Button authorize; // Value injected by FXMLLoader

    @FXML // fx:id="error_text"
    private Text error_text; // Value injected by FXMLLoader

    @FXML // fx:id="login"
    private TextField login; // Value injected by FXMLLoader

    @FXML // fx:id="password"
    private PasswordField password; // Value injected by FXMLLoader

    @FXML // fx:id="register"
    private Button register; // Value injected by FXMLLoader

    @FXML
    void login(ActionEvent event) {
        sendLoginRequest(login.getText(), password.getText());
    }

    @SneakyThrows
    private void sendLoginRequest(String username, String password) {

        AuthenticationResponse authenticationResponse = authenticationRestClient.login(username, password);

        if (authenticationResponse.isError()) {
            error_text.setText(authenticationResponse.getToken());
            return;
        }

        RuntimeConfiguration.setJwtToken(authenticationResponse.getToken());
        error_text.setText("");

        try {
            stageHandler.showWorkspaceScene();
        } catch (Exception e) {
            throw new RuntimeException(e);
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


