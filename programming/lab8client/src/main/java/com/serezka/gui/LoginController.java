package com.serezka.gui;

import com.serezka.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

public class LoginController {
    @FXML // fx:id="authorize"
    private Button authorize; // Value injected by FXMLLoader

    @FXML // fx:id="error_text"
    private Text error_text; // Value injected by FXMLLoader

    @FXML // fx:id="login"
    private TextField login; // Value injected by FXMLLoader

    @FXML // fx:id="password"
    private PasswordField password; // Value injected by FXMLLoader

    private static final String SERVER_URL = "http://localhost:1337/login";

    @FXML
    void login(ActionEvent event) {
        sendLoginRequest(login.getText(), password.getText());
    }

    private void sendLoginRequest(String username, String password) {
        try {
            Main.showWorkspaceScene();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//
//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(SERVER_URL + "?username=" + username + "&password=" + password))
//                .GET()
//                .build();
//
//        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
//                .thenApply(HttpResponse::body)
//                .thenAccept(response -> {
//                    if (response.contains("Success")) { // Предполагаем, что успешный ответ содержит "Success"
//                        Platform.runLater(() -> {
//                            try {
//                                Main.showWorkspaceScene(); // Показываем окно рабочего пространства
//                            } catch (Exception e) {
//                                e.printStackTrace(); // Логируем ошибку, если что-то пошло не так
//                            }
//                        });
//                    } else {
//                        Platform.runLater(() -> error_text.setText("Login Failed!"));
//                    }
//                })
//                .exceptionally(e -> {
//                    e.printStackTrace();
//                    return null;
//                });
    }
}


