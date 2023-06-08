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

import java.io.IOException;


public class RegisterController {

    @FXML
    public TextField cnpField;
    @FXML
    public TextField emailField;
    @FXML
    public TextField telefonField;
    @FXML
    public TextField passwordField;
    @FXML
    public Button backButton;
    @FXML
    public Button signInButton;
    @FXML
    public Label errorLabel;
    @FXML
    public TextField numeField;

    Service service;

    public void setService(Service service){
        this.service = service;
    }

    @FXML
    private void backClick() throws IOException {

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

    public void signInClick(ActionEvent actionEvent) throws IOException  {
        String cnp = cnpField.getText();
        String email = emailField.getText();
        String telefon = telefonField.getText();
        String password = passwordField.getText();
        String nume = numeField.getText();

        // Validate the input fields
        if (cnp.isEmpty() || email.isEmpty() || telefon.isEmpty() || password.isEmpty() || nume.isEmpty()) {
            errorLabel.setText("All fields must be filled!");
        }else {
            try {
                service.addAbonat(cnp, email, nume, telefon, password);
                errorLabel.setText("Successfully registered!");
            } catch (Exception e) {
                errorLabel.setText("Registration failed: " + e.getMessage());
            }
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
    }

    private void close() {
        Stage thisStage = (Stage) cnpField.getScene().getWindow();
        thisStage.close();
    }


}
