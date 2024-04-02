/**
 * Sample Skeleton for 'workspace.fxml' Controller Class
 */

package com.serezka.gui.controller;

import com.serezka.configuration.RuntimeConfiguration;
import com.serezka.gui.stage.StageHandler;
import com.serezka.net.collection.CollectionRestClient;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class WorkspaceController {
    private final CollectionRestClient collectionRestClient;
    private final StageHandler stageHandler;

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
        flatsData.addAll(collectionRestClient.findAll());


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

    private TextField getField(String prompt) {
        TextField textField = new TextField();
        textField.setPromptText(prompt);
        return textField;
    }

    private void showAddDialog() {
        Stage popup = new Stage();

        popup.initModality(Modality.APPLICATION_MODAL);
        popup.setTitle("New Element");


        HBox infoBox = new HBox(5);

        TextField nameField = getField("Name");
        TextField areaField = getField("Area");

        infoBox.getChildren().addAll(nameField, areaField);

        HBox coordinatesBox = new HBox(5);

        TextField coordinatesX = getField("Cord X");
        TextField coordinatesY = getField("Cord Y");
        coordinatesBox.getChildren().addAll(coordinatesX, coordinatesY);

        HBox flatInfo = new HBox(5);

        TextField numberOfRoomsField = getField("Number of Rooms");
        TextField livingSpaceField = getField("Living Space");

        flatInfo.getChildren().addAll(numberOfRoomsField, livingSpaceField);

        VBox selectors = new VBox(5);

        CheckBox furnitureCheckBox = new CheckBox("Furniture");

        HBox transportSelector = new HBox(5);
        Label label = new Label("Transport");

        ComboBox<Transport> transportComboBox = new ComboBox<>();
        transportComboBox.setItems(FXCollections.observableArrayList(Arrays.asList(Transport.values())));

        transportSelector.getChildren().addAll(label, transportComboBox);
        selectors.getChildren().addAll(furnitureCheckBox, transportSelector);

        VBox houseBox = new VBox(5);
        HBox houseLine1 = new HBox(5);
        HBox houseLine2 = new HBox(5);

        TextField houseNameField = getField("House Name");
        TextField houseYearField = getField("House Year");
        houseLine1.getChildren().addAll(houseNameField, houseYearField);

        TextField houseNumberOfFloorsField = getField("House Number of Floors");
        TextField houseNumberOfLiftsField = getField("House Number of Lifts");
        houseLine2.getChildren().addAll(houseNumberOfFloorsField, houseNumberOfLiftsField);

        houseBox.getChildren().addAll(houseLine1, houseLine2);

        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> {
            try {
                Flat flat = new Flat();

                flat.setName(nameField.getText());
                flat.setArea(Long.valueOf(areaField.getText()));

                Coordinates coordinates = new Coordinates();
                coordinates.setX(Float.valueOf(coordinatesX.getText()));
                coordinates.setY(Long.valueOf(coordinatesY.getText()));

                flat.setCoordinates(coordinates);

                flat.setNumberOfRooms(Integer.valueOf(numberOfRoomsField.getText()));
                flat.setLivingSpace(Double.valueOf(livingSpaceField.getText()));

                flat.setFurniture(furnitureCheckBox.isSelected());
                flat.setTransport(transportComboBox.getValue());

                House house = new House();
                house.setName(houseNameField.getText());
                house.setYear(Integer.valueOf(houseYearField.getText()));
                house.setNumberOfFlatsOnFloor(Long.parseLong(houseNumberOfFloorsField.getText()));
                house.setNumberOfLifts(Integer.parseInt(houseNumberOfLiftsField.getText()));

                flat.setHouse(house);

                collectionRestClient.addFlat(flat);

            } catch (RequirementsException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Validation failed: " + ex.getMessage());
                alert.showAndWait();
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid input or operation failed: " + ex.getMessage());
                alert.showAndWait();
            }
        });

        VBox layout = new VBox(5, infoBox,coordinatesBox,flatInfo,selectors,houseBox,saveButton);
        layout.setPadding(new Insets(10, 10, 10, 10));
        popup.setScene(new javafx.scene.Scene(layout, 300, 250));
        popup.showAndWait();
    }

    @SneakyThrows
    @FXML
    void logout(ActionEvent event) {
        RuntimeConfiguration.setJwtToken("");
        stageHandler.showLoginScene();
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
