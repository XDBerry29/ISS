package com.example.bibliotecaiss.controller;

import com.example.bibliotecaiss.HelloApplication;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class BibleotecarMainController {
    @FXML
    public TableView<Exemplar> tabelCarti;
    @FXML
    public Button addBookButton, returnButton, imprumutaButton, inregistreazaButton, logOutButton, ExtindeImprumutButton;
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
    

    public void setService(Service service){
        this.service = service;
        initModel();
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

    public void handleInregistreaza(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("views/RegisterBibleotecarView.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Sign Up");
        BibleotecarSignUpController bibleotecarSignUpController = fxmlLoader.getController();
        bibleotecarSignUpController.setService(service);
        stage.show();
        close();

    }

    public void handleLogOut(ActionEvent actionEvent) throws IOException {
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

    public void handleExtinde(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("views/ExtindeView.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Extinde Page");
        ExtindeController controller = fxmlLoader.getController();
        controller.setService(service);
        stage.show();
        close();
    }

    public void handleAddBook(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("views/AdaugaCarteView.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Adauga Page");
        AdaugaCarteController controller = fxmlLoader.getController();
        controller.setService(service);
        stage.show();
        close();
    }

    public void handleReturn(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("views/ReturnView.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Return Page");
        ReturnController controller = fxmlLoader.getController();
        controller.setService(service);
        stage.show();
        close();
    }

    public void handleImprumuta(ActionEvent actionEvent) throws IOException {
        Exemplar exemplar = tabelCarti.getSelectionModel().getSelectedItem();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("views/ImprumutaView.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Imprumut Page");
        ImprumutaController controller = fxmlLoader.getController();
        controller.setService(service);
        controller.setExemplar(exemplar);
        stage.show();
        close();

    }

    private void close() {
        Stage thisStage = (Stage) logOutButton.getScene().getWindow();
        thisStage.close();
    }
}
