/**
 * Sample Skeleton for 'workspace.fxml' Controller Class
 */

package com.serezka.gui;

import com.serezka.net.methods.collection.FindAll;
import com.serezka.objects.Coordinates;
import com.serezka.objects.Flat;
import com.serezka.objects.House;
import com.serezka.objects.Transport;
import com.serezka.objects.exceptions.RequirementsException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.SneakyThrows;

import java.util.GregorianCalendar;

public class WorkspaceController {

    @FXML // fx:id="add"
    private Button add; // Value injected by FXMLLoader

    @FXML // fx:id="info"
    private Button info; // Value injected by FXMLLoader

    @FXML // fx:id="logout"
    private Button logout; // Value injected by FXMLLoader

    @FXML // fx:id="mainTable"
    private TableView<Flat> mainTable; // Value injected by FXMLLoader

    @FXML // fx:id="remove"
    private Button remove; // Value injected by FXMLLoader

    @FXML // fx:id="removeAll"
    private Button removeAll; // Value injected by FXMLLoader

    @FXML // fx:id="show_onlyredactable"
    private ToggleButton show_onlyredactable; // Value injected by FXMLLoader

    @FXML // fx:id="username"
    private Label username; // Value injected by FXMLLoader

    private final ObservableList<Flat> flatsData = FXCollections.observableArrayList();

    @SneakyThrows
    public void initialize() {
        flatsData.addAll(new FindAll().execute());


        initializeTableColumns();
        mainTable.setItems(flatsData);
    }

    private void initializeTableColumns() {
        // Пример инициализации колонок. Дополните по необходимости для других полей.
        TableColumn<Flat, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Flat, Long> areaColumn = new TableColumn<>("Area");
        areaColumn.setCellValueFactory(new PropertyValueFactory<>("area"));

        // Для примера добавим только две колонки. Добавьте остальные самостоятельно.
        mainTable.getColumns().addAll(nameColumn, areaColumn);
    }

    @FXML
    void add(ActionEvent event) {
        showAddDialog();
    }

    private void showAddDialog() {
        Stage popup = new Stage();
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.setTitle("Add New Flat");

        TextField nameField = new TextField();
        nameField.setPromptText("Name");

        TextField areaField = new TextField();
        areaField.setPromptText("Area");

        // Примеры для других полей. Должны быть адаптированы и дополнены в соответствии с вашими классами.
        TextField numberOfRoomsField = new TextField();
        numberOfRoomsField.setPadding(new Insets(10));
        numberOfRoomsField.setPromptText("Number of Rooms");

        TextField livingSpaceField = new TextField();
        livingSpaceField.setPromptText("Living Space");

        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> {
            try {
                // Попытка создать и добавить новый объект Flat, используя поля ввода.
                Flat flat = new Flat();
                flat.setName(nameField.getText());
                flat.setArea(Long.parseLong(areaField.getText()));
                flat.setNumberOfRooms(Integer.parseInt(numberOfRoomsField.getText()));
                flat.setLivingSpace(Double.parseDouble(livingSpaceField.getText()));

                // Здесь добавляется код для создания и установки объекта Coordinates и House
                // Пример:
                // Coordinates coordinates = new Coordinates();
                // coordinates.setX(Float.valueOf(xField.getText()));
                // coordinates.setY(Long.valueOf(yField.getText()));
                // flat.setCoordinates(coordinates);

                // Для House аналогично...

                // Проверка на валидность и добавление в коллекцию
                if (flat.validate()) { // Предполагаем, что validate возвращает true или выбрасывает исключение
                    flatsData.add(flat);
                    popup.close();
                }
            } catch (RequirementsException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Validation failed: " + ex.getMessage());
                alert.showAndWait();
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid input or operation failed: " + ex.getMessage());
                alert.showAndWait();
            }
        });

        VBox layout = new VBox(10, nameField, areaField, numberOfRoomsField, livingSpaceField, saveButton);
        popup.setScene(new javafx.scene.Scene(layout, 300, 250));
        popup.showAndWait();
    }

    @FXML
    void logout(ActionEvent event) {

    }

    @FXML
    void removeAll(ActionEvent event) {

    }

    @FXML
    void removeSelected(ActionEvent event) {

    }

    @FXML
    void setViewOnlyRedactable(ActionEvent event) {

    }

    @FXML
    void showInfo(ActionEvent event) {

    }

}
