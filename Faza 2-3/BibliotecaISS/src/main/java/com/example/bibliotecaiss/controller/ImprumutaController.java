package com.example.bibliotecaiss.controller;

import com.example.bibliotecaiss.HelloApplication;
import com.example.bibliotecaiss.model.Abonat;
import com.example.bibliotecaiss.model.Exemplar;
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

public class ImprumutaController {

    @FXML
    private Button backButton;

    @FXML
    private Button confirmButton;

    @FXML
    private TextField cnpField;

    private Exemplar exemplar;

    private Service service;

    public void setService(Service service){
        this.service = service;
    }

    public void setExemplar(Exemplar exemplar){
        this.exemplar=exemplar;
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
    private void handleConfirm(ActionEvent event) {
        String cnp = cnpField.getText();
        Abonat abonat = service.findAbonatByCNP(cnp);
        try {
            service.imprumutaExemplar(abonat,exemplar);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Imprumutul s-a efectuat pe 7 zile!");
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
        Stage thisStage = (Stage) cnpField.getScene().getWindow();
        thisStage.close();
    }
}
