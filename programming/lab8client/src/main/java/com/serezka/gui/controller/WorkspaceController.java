/**
 * Sample Skeleton for 'workspace.fxml' Controller Class
 */

package com.serezka.gui.controller;

import com.serezka.configuration.RuntimeConfiguration;
import com.serezka.gui.stage.StageHandler;
import com.serezka.localization.Localization;
import com.serezka.net.collection.CollectionRestClient;
import com.serezka.net.execution.ExecutionRestClient;
import com.serezka.net.execution.Response;
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
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WorkspaceController {
    // rest clients
    final CollectionRestClient collectionRestClient;
    final ExecutionRestClient executionRestClient;

    // localization
    final Localization localization;

    // stage handler
    final StageHandler stageHandler;

    // FXML fields
    @FXML ResourceBundle resources;
    @FXML URL location;
    @FXML Button add;
    @FXML Button info;
    @FXML Button logout;
    @FXML TableView<Flat> mainTable;
    @FXML Button remove;
    @FXML Button removeAll;
    @FXML ToggleButton showOnlyRedactable;
    @FXML Label status;
    @FXML MenuItem console;
    @FXML ChoiceBox<String> language;

    /* table data */ final ObservableList<Flat> flatsData = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     * Automatically called after FXML loading.
     */
    @SneakyThrows
    public void initialize() {
        language.setItems(FXCollections.observableArrayList(Arrays.stream(Localization.Type.values()).map(Localization.Type::getName).toList()));
        language.setValue(Localization.Type.DEFAULT.getName());

        language.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                RuntimeConfiguration.setLocalizationType(Localization.Type.fromString(newValue));
                refreshUI();
            }
        });

        mainTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && !mainTable.getSelectionModel().isEmpty() && mainTable.getSelectionModel().getSelectedItem().isEditable())
                showEditDialog();
        });

        mainTable.setRowFactory(tv -> new TableRow<>() {
            @Override
            protected void updateItem(Flat item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) return;
                if (!item.isEditable()) return;

                setStyle("-fx-font-weight: bold; -fx-text-inner-color: red;");
            }
        });

        updateTable();
        refreshUI();
    }

    private void refreshUI() {

        // buttons
        add.setText(localization.get("button.add"));
        info.setText(localization.get("button.info"));
        logout.setText(localization.get("button.logout"));
        remove.setText(localization.get("button.remove"));
        removeAll.setText(localization.get("button.removeAll"));
        showOnlyRedactable.setText(localization.get("toggle.showOnlyEditable"));
    }

    /**
     * Updates table data
     * Called after any action that changes table data
     */
    private void updateTable() {
        flatsData.clear();
        flatsData.addAll(collectionRestClient.findAll());

        mainTable.getColumns().clear();
        initializeTableColumns();
        if (showOnlyRedactable.isSelected())
            flatsData.removeIf(flat -> !flat.isEditable());
        mainTable.setItems(flatsData);
        mainTable.refresh();
    }

    /**
     * Initializes table columns
     */
    private void initializeTableColumns() {
        TableColumn<Flat, String> nameColumn = new TableColumn<>("name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Flat, Long> coordinates = new TableColumn<>("cords");
        coordinates.setCellValueFactory(new PropertyValueFactory<>("coordinates"));

        TableColumn<Flat, Long> creationDateColumn = new TableColumn<>("created");
        creationDateColumn.setCellValueFactory(new PropertyValueFactory<>("creationDate"));

        TableColumn<Flat, Long> areaColumn = new TableColumn<>("area");
        areaColumn.setCellValueFactory(new PropertyValueFactory<>("area"));

        TableColumn<Flat, Long> numberOfRoomsColumn = new TableColumn<>("rooms");
        numberOfRoomsColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfRooms"));

        TableColumn<Flat, Long> livingSpaceColumn = new TableColumn<>("living space");
        livingSpaceColumn.setCellValueFactory(new PropertyValueFactory<>("livingSpace"));

        TableColumn<Flat, Long> furnitureColumn = new TableColumn<>("furniture");
        furnitureColumn.setCellValueFactory(new PropertyValueFactory<>("furniture"));

        TableColumn<Flat, Long> transportColumn = new TableColumn<>("transport");
        transportColumn.setCellValueFactory(new PropertyValueFactory<>("transport"));

        TableColumn<Flat, Long> houseColumn = new TableColumn<>("house");
        houseColumn.setCellValueFactory(new PropertyValueFactory<>("house"));

        // Для примера добавим только две колонки. Добавьте остальные самостоятельно.
        mainTable.getColumns().addAll(
                nameColumn,
                areaColumn,
                coordinates,
                creationDateColumn,
                numberOfRoomsColumn,
                livingSpaceColumn,
                furnitureColumn,
                transportColumn,
                houseColumn);
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

    private TextField getField(String prompt, String value) {
        TextField textField = new TextField();
        textField.setPromptText(prompt);
        textField.setText(value);
        return textField;
    }

    private void showEditDialog() {
        Flat selected = mainTable.getSelectionModel().getSelectedItem();

        Stage popup = new Stage();

        popup.initModality(Modality.APPLICATION_MODAL);
        popup.setTitle(localization.get("title.editElement"));

        HBox infoBox = new HBox(5);

        TextField nameField = getField(localization.get("prompt.name"), selected.getName());
        TextField areaField = getField(localization.get("prompt.area"), String.valueOf(selected.getArea()));

        infoBox.getChildren().addAll(nameField, areaField);

        HBox coordinatesBox = new HBox(5);

        TextField coordinatesX = getField(localization.get("prompt.cord.x"), String.valueOf(selected.getCoordinates().getX()));
        TextField coordinatesY = getField(localization.get("prompt.cord.y"), String.valueOf(selected.getCoordinates().getY()));
        coordinatesBox.getChildren().addAll(coordinatesX, coordinatesY);

        HBox flatInfo = new HBox(5);

        TextField numberOfRoomsField = getField(localization.get("prompt.numberOfRooms"), String.valueOf(selected.getNumberOfRooms()));
        TextField livingSpaceField = getField(localization.get("prompt.livingSpace"), String.valueOf(selected.getLivingSpace()));

        flatInfo.getChildren().addAll(numberOfRoomsField, livingSpaceField);

        VBox selectors = new VBox(5);

        CheckBox furnitureCheckBox = new CheckBox(localization.get("prompt.furniture"));
        furnitureCheckBox.setSelected(selected.isFurniture());

        HBox transportSelector = new HBox(5);
        Label label = new Label(localization.get("prompt.transport"));

        ComboBox<Transport> transportComboBox = new ComboBox<>();
        transportComboBox.setItems(FXCollections.observableArrayList(Arrays.asList(Transport.values())));
        transportComboBox.setValue(selected.getTransport());

        transportSelector.getChildren().addAll(label, transportComboBox);
        selectors.getChildren().addAll(furnitureCheckBox, transportSelector);

        VBox houseBox = new VBox(5);
        HBox houseLine1 = new HBox(5);
        HBox houseLine2 = new HBox(5);

        TextField houseNameField = getField(localization.get("prompt.houseName"), selected.getHouse().getName());
        TextField houseYearField = getField(localization.get("prompt.houseYear"), String.valueOf(selected.getHouse().getYear()));
        houseLine1.getChildren().addAll(houseNameField, houseYearField);

        TextField houseNumberOfFloorsField = getField(localization.get("prompt.houseNumberOfFloors"), String.valueOf(selected.getHouse().getNumberOfFlatsOnFloor()));
        TextField houseNumberOfLiftsField = getField(localization.get("prompt.houseNumberOfLifts"), String.valueOf(selected.getHouse().getNumberOfLifts()));
        houseLine2.getChildren().addAll(houseNumberOfFloorsField, houseNumberOfLiftsField);

        houseBox.getChildren().addAll(houseLine1, houseLine2);

        HBox buttonsBox = new HBox(5);

        Button editButton = new Button(localization.get("button.edit"));
        editButton.setDefaultButton(true);

        Button closeButton = new Button(localization.get("button.close"));
        closeButton.setCancelButton(true);

        buttonsBox.getChildren().addAll(editButton, closeButton);

        closeButton.setOnAction(e -> popup.close());

        editButton.setOnAction(e -> {
            try {
                Flat flat = new Flat();

                flat.setId(mainTable.getSelectionModel().getSelectedItem().getId());

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
                popup.close();
                status.setText(localization.get("notification.element.updated"));
                updateTable();
            } catch (RequirementsException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, localization.get("error.validation.failed") + ": " + ex.getMessage());
                alert.showAndWait();
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, localization.get("error.invalidInput") + ": " + ex.getMessage());
                alert.showAndWait();
            }
        });

        VBox layout = new VBox(5, infoBox, coordinatesBox, flatInfo, selectors, houseBox, buttonsBox);
        layout.setPadding(new Insets(10, 10, 10, 10));
        popup.setScene(new javafx.scene.Scene(layout, 300, 250));
        popup.showAndWait();
    }

    private void showAddDialog() {
        Stage popup = new Stage();

        popup.initModality(Modality.APPLICATION_MODAL);
        popup.setTitle(localization.get("popup.addElement"));

        HBox infoBox = new HBox(5);

        TextField nameField = getField(localization.get("prompt.name"));
        TextField areaField = getField(localization.get("prompt.area"));

        infoBox.getChildren().addAll(nameField, areaField);

        HBox coordinatesBox = new HBox(5);

        TextField coordinatesX = getField(localization.get("prompt.cord.x"));
        TextField coordinatesY = getField(localization.get("prompt.cord.y"));
        coordinatesBox.getChildren().addAll(coordinatesX, coordinatesY);

        HBox flatInfo = new HBox(5);

        TextField numberOfRoomsField = getField(localization.get("prompt.numberOfRooms"));
        TextField livingSpaceField = getField(localization.get("prompt.livingSpace"));

        flatInfo.getChildren().addAll(numberOfRoomsField, livingSpaceField);

        VBox selectors = new VBox(5);

        CheckBox furnitureCheckBox = new CheckBox(localization.get("prompt.furniture"));

        HBox transportSelector = new HBox(5);
        Label label = new Label(localization.get("prompt.transport"));

        ComboBox<Transport> transportComboBox = new ComboBox<>();
        transportComboBox.setItems(FXCollections.observableArrayList(Arrays.asList(Transport.values())));

        transportSelector.getChildren().addAll(label, transportComboBox);
        selectors.getChildren().addAll(furnitureCheckBox, transportSelector);

        VBox houseBox = new VBox(5);
        HBox houseLine1 = new HBox(5);
        HBox houseLine2 = new HBox(5);

        TextField houseNameField = getField(localization.get("prompt.houseName"));
        TextField houseYearField = getField(localization.get("prompt.houseYear"));
        houseLine1.getChildren().addAll(houseNameField, houseYearField);

        TextField houseNumberOfFloorsField = getField(localization.get("prompt.houseNumberOfFloors"));
        TextField houseNumberOfLiftsField = getField(localization.get("prompt.houseNumberOfLifts"));
        houseLine2.getChildren().addAll(houseNumberOfFloorsField, houseNumberOfLiftsField);

        houseBox.getChildren().addAll(houseLine1, houseLine2);

        HBox buttonsBox = new HBox(5);

        Button saveButton = new Button(localization.get("button.save"));
        saveButton.setDefaultButton(true);

        Button closeButton = new Button(localization.get("button.close"));
        closeButton.setCancelButton(true);

        buttonsBox.getChildren().addAll(saveButton, closeButton);

        closeButton.setOnAction(e -> popup.close());

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
                popup.close();
                status.setText(localization.get("element.added"));
                updateTable();
            } catch (RequirementsException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, localization.get("error.validation.failed") + ": " + ex.getMessage());
                alert.showAndWait();
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, localization.get("error.invalidInput") + ": " + ex.getMessage());
                alert.showAndWait();
            }
        });

        VBox layout = new VBox(5, infoBox, coordinatesBox, flatInfo, selectors, houseBox, buttonsBox);
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
        status.setText(collectionRestClient.clear());
        updateTable();
    }

    @FXML
    void removeSelected(ActionEvent event) {
        Flat selected = mainTable.getSelectionModel().getSelectedItem();
        status.setText(collectionRestClient.remove(selected));
        updateTable();
    }

    @FXML
    void setViewOnlyRedactable(ActionEvent event) {
        if (showOnlyRedactable.isSelected()) {
            flatsData.clear();
            flatsData.addAll(collectionRestClient.findAll());
            flatsData.removeIf(flat -> !flat.isEditable());
        } else {
            updateTable();
        }
    }

    @FXML
    void showInfo(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(localization.get("information.header"));
        alert.setHeaderText(localization.get("information.title"));
        alert.setContentText(/*"I <3 JAVA\nCreated by serezk4\n2024 & github.com/serezk4"*/localization.get("information.content"));
        alert.showAndWait();
    }


    @FXML
    void summonConsole(ActionEvent event) {
        Stage consolePopup = new Stage();

        consolePopup.initModality(Modality.APPLICATION_MODAL);
        consolePopup.setTitle(localization.get("title.console"));

        VBox layout = new VBox(10);
        TextArea commandInput = new TextArea();
        commandInput.setPromptText(/*"Enter your command here..."*/localization.get("prompt.command"));

        TextArea commandOutput = new TextArea();
        commandOutput.setEditable(false);

        Button executeButton = new Button(localization.get("button.execute"));
        executeButton.setOnAction(q -> {
            Response response = executionRestClient.executeCommand(commandInput.getText(), null, null);
            commandOutput.setText(response.getCode());
        });

        layout.getChildren().addAll(commandInput, executeButton, commandOutput);
        VBox.setVgrow(commandOutput, Priority.ALWAYS);

        consolePopup.setScene(new Scene(layout, 600, 400));
        consolePopup.showAndWait();
    }
}
