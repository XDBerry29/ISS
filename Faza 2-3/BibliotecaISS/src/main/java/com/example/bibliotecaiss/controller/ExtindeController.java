package com.example.bibliotecaiss.controller;

import com.example.bibliotecaiss.HelloApplication;
import com.example.bibliotecaiss.model.Service;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ExtindeController {

    @FXML
    private Button backButton;

    @FXML
    private Button confirmButton;

    @FXML
    private TextField codExemplarFiled;

    @FXML
    private TextField zileField;

    private Service service;

    public void setService(Service service){
        this.service = service;
    }



    @FXML
    private void handleBack(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("views/BibliotecarMain.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Bibleotecar Main");
        BibleotecarMainController loginController = fxmlLoader.getController();
        loginController.setService(service);
        stage.show();
        close();
    }

    @FXML
    private void handleReturn(ActionEvent event) {
        Long codExemplar = Long.parseLong(codExemplarFiled.getText());
        int nrZile = Integer.parseInt(zileField.getText());

        try {
            service.extendLoan(codExemplar, nrZile);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Imprumutul s-a extins cu "+nrZile+" zile!");
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("There was an error: " + e.getMessage());
            alert.showAndWait();
        }
    }

    private void close() {
        Stage thisStage = (Stage) backButton.getScene().getWindow();
        thisStage.close();
    }
}
