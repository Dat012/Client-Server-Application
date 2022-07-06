package com.example.courseproject1;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Authorization {

    Application application;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button signInButton;

    @FXML
    private TextField loginField;

    @FXML
    private Button signUpButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    void signInButtonListener() {

    }

    @FXML
    void signUpButtonListener(ActionEvent event) {
        Application.changeScene("registration.fxml");
    }

    @FXML
    void checkAuthorizationData() {

    }

}
