package com.example.bibliotecaiss;

import com.example.bibliotecaiss.controller.LoginController;
import com.example.bibliotecaiss.model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class HelloApplication extends Application {

    AbonatDbRepo repoAbonat;
    CarteDbRepo repoCarte;
    BibliotecarDbRepo repoBibliotecar;

    ExemplarDbRepo repoExemplar;

    ImprumutDbRepo repoImprumut;
    Service service;

    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
        repoAbonat = new AbonatDbRepo();
        repoCarte = new CarteDbRepo();
        repoBibliotecar = new BibliotecarDbRepo();
        repoExemplar = new ExemplarDbRepo();
        repoImprumut = new ImprumutDbRepo();

        service = new Service(repoCarte, repoAbonat, repoBibliotecar,repoExemplar,repoImprumut);
        loginView(stage);
        stage.show();
    }

    private void loginView(Stage stage) throws IOException {

        URL fxmlLocation = HelloApplication.class.getResource("views/LoginView.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
        TitledPane Layout = fxmlLoader.load();
        stage.setScene(new Scene(Layout));
        stage.setTitle("Login Page");

        LoginController loginController = fxmlLoader.getController();
        loginController.setService(service);
    }

    public static void main(String[] args) {
        launch();
    }
}