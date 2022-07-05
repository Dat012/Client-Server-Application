package com.example.courseproject1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Registration {

    @FXML
    private Button backButton;

    @FXML
    private Label checkPassword;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signUpButton;

    @FXML
    void backButtonListener(ActionEvent event) {
        backButton.setOnAction(actionEvent -> {
            Application.changeScene("authorization.fxml");
        });
    }

    @FXML
    void signUpButtonListener(ActionEvent event) {
        signUpButton.setOnAction(actionEvent -> {

        });
    }

}

