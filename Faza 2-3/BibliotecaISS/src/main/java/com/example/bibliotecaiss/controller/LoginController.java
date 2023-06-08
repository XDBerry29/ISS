package com.example.bibliotecaiss.controller;

import com.example.bibliotecaiss.HelloApplication;
import com.example.bibliotecaiss.model.Service;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;

public class LoginController {
    @FXML
    public TextField emailField;
    @FXML
    public TextField parolaField;
    @FXML
    public Button loginButton;
    @FXML
    public Button signupButton;

    @FXML
    public Label errorLabel;

    Service service;

    public void setService(Service service){
        this.service = service;
    }


    public void handleLogin(ActionEvent actionEvent) throws IOException {
        String email = emailField.getText();
        String parola = parolaField.getText();

        int result = service.checkFields(email, parola);

        FXMLLoader fxmlLoader = null;
            switch (result) {
                case 1:
                    fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("views/AbonatMain.fxml"));
                    Parent root = fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Abonat Page");
                    AbonatMainController abonatMainController = fxmlLoader.getController();
                    abonatMainController.setService(service);
                    abonatMainController.setAbonat(service.findAbonat(email));
                    stage.show();
                    close();
                    break;
                case 2:
                    errorLabel.setText("Bibleotecat!");
                    fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("views/BibliotecarMain.fxml"));
                    root = fxmlLoader.load();
                    stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Bibliotecar Page");
                    BibleotecarMainController bibleotecarMainController = fxmlLoader.getController();
                    bibleotecarMainController.setService(service);
                    stage.show();
                    close();
                    break;
                default:
                    errorLabel.setText("Invalid credetials!");
                    break;
            };
    }

    private void close() {
        Stage thisStage = (Stage) emailField.getScene().getWindow();
        thisStage.close();
    }

    public void handleSignIn(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = null;
        fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("views/RegisterView.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Login Page");
        RegisterController reginController = fxmlLoader.getController();
        reginController.setService(service);
        stage.show();
        close();
    }
}
