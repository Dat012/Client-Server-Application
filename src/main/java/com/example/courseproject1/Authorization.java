package com.example.courseproject1;

import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;

public class Authorization {

    Application application;

    User user;

    Connection connection;
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
    private Label checkPassword;

    @FXML
    void signInButtonListener() {
        try {
            user = new User();
            String login = loginField.getText();
            String hashPassword = user.makeHash(passwordField.getText());

            if (Objects.equals(login, "") || Objects.equals(hashPassword, "")) {
                checkPassword.setTextFill(Paint.valueOf("RED"));
                checkPassword.setText("Please fill in all the fields");
            } else {
                connection = Application.connectToDatabase();
                String query = String.format("SELECT * FROM user WHERE login = '%s' AND password_hash = '%s';", login, hashPassword);
                ResultSet resultSet = Application.executeSQL(query, connection);
                if (resultSet.next()) {
                    Application.changeScene("menu.fxml");
                } else {
                    checkPassword.setTextFill(Paint.valueOf("RED"));
                    checkPassword.setText("Wrong username or password");
                }
                resultSet.close();
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQLException при создании statement в регистрации");
        }
    }

    @FXML
    void signUpButtonListener(ActionEvent event) {
        Application.changeScene("registration.fxml");
    }

    @FXML
    void checkAuthorizationData() {

    }

}
