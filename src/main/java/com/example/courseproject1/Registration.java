package com.example.courseproject1;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.Objects;

public class Registration {

    private User user;

    @FXML
    private Button backButton;

    @FXML
    private Label checkPassword;

    @FXML
    private TextField loginField;

    @FXML
    private TextField groupField;

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
        //Connection connection = Application.connectToDatabase();
        try {
            // Statement statement = connection.createStatement();
            user = new User();
            String login = loginField.getText();
            String hashPassword = user.makeHash(passwordField.getText());
            String groupString = groupField.getText();

            if (Objects.equals(login, "") || Objects.equals(hashPassword, "") || Objects.equals(groupString, "")) {
                checkPassword.setTextFill(Paint.valueOf("RED"));
                checkPassword.setText("Please fill in all the fields");
            } else {
                int group = Integer.parseInt(groupString);
                String query = String.format("INSERT INTO user(login, password_hash, status, gang) VALUES ('%s', '%s', %d, %d);", login, hashPassword, 3, group);
                try {
                    // statement.execute(query);
                    Application.executeSQL(query);
                    checkPassword.setTextFill(Paint.valueOf("GREEN"));
                    checkPassword.setText("You have successfully registered");
                } catch (SQLIntegrityConstraintViolationException e) {
                    checkPassword.setTextFill(Paint.valueOf("RED"));
                    checkPassword.setText("This login already exists");
                }
            }

            //connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQLException при создании statement в регистрации");
        }
    }

    @FXML
    public void initialize() {
        groupField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    groupField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

    }

}

