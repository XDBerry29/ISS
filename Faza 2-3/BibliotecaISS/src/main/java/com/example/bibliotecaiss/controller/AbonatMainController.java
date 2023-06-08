package com.example.bibliotecaiss.controller;

import com.example.bibliotecaiss.HelloApplication;
import javafx.scene.control.Alert.AlertType;
import com.example.bibliotecaiss.model.Abonat;
import com.example.bibliotecaiss.model.Carte;
import com.example.bibliotecaiss.model.Exemplar;
import com.example.bibliotecaiss.model.Service;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class AbonatMainController {
    @FXML
    public TableView<Exemplar> tabelCarti;
    @FXML
    public Button imprumutaButton;
    @FXML
    public Button logOutButton;
    @FXML
    public TableColumn<Exemplar, Long> codexColumn;
    @FXML
    public TableColumn<Exemplar, String> titluColumn;
    @FXML
    public TableColumn<Exemplar, String> autorColumn;
    @FXML
    public TableColumn<Exemplar, String> genColumn;

    private ObservableList<Exemplar> modelExemplar = FXCollections.observableArrayList();

    Service service;

    Abonat abonat;

    public void setService(Service service){
        this.service = service;
        initModel();
    }

    public void setAbonat(Abonat abonat){
        this.abonat = abonat;
    }

    @FXML
    public void initialize() {
        codexColumn.setCellValueFactory(new PropertyValueFactory<>("cod_unic"));
        titluColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCarte().getTitlu()));
        autorColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCarte().getAutor()));
        genColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCarte().getGen()));

        tabelCarti.setItems(modelExemplar);
    }

    private void initModel() {
        modelExemplar.setAll();
        modelExemplar.addAll(service.getAllAvailableExemplare());
    }


    @FXML
    public void handleImprumuta(ActionEvent actionEvent) {
        Exemplar exemplar = tabelCarti.getSelectionModel().getSelectedItem();
        try {
            service.imprumutaExemplar(abonat,exemplar);
            initModel();

            // Show a success message
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Imprumutarea s-a facut cu succes pt. 7 zile!\n" +
                    "Mergeti la receptie pentru a ridica cartea!");
            alert.showAndWait();
        } catch (Exception e) {
            // Handle exceptions that might occur when borrowing an exemplar
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("There was an error during the process: " + e.getMessage());
            alert.showAndWait();
        }
    }

    @FXML
    public void handlerLogOut(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("views/LoginView.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Login Page");
        LoginController loginController = fxmlLoader.getController();
        loginController.setService(service);
        stage.show();
        close();
    }

    private void close() {
        Stage thisStage = (Stage) logOutButton.getScene().getWindow();
        thisStage.close();
    }
}
