package com.example.courseproject1.Controllers;

import com.example.courseproject1.Application;
import com.example.courseproject1.User;
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

import java.sql.*;
import java.util.Objects;

public class Registration {

    User user;

    Connection connection;
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
        Application.changeScene("authorization.fxml");
    }

    @FXML
    void signUpButtonListener(ActionEvent event) {
        try {
            // Statement statement = connection.createStatement();
            String login = loginField.getText();
            String hashPassword = User.makeHashStatic(passwordField.getText());
            String groupString = groupField.getText();

            if (Objects.equals(login, "") || Objects.equals(hashPassword, "") || Objects.equals(groupString, "")) {
                checkPassword.setTextFill(Paint.valueOf("RED"));
                checkPassword.setText("Please fill in all the fields");
            } else {
                int group = Integer.parseInt(groupString);
                String query = "INSERT INTO user(login, password_hash, status, gang) VALUES (?, ?, ?, ?);";//, login, hashPassword, 3, group);
                try {

                    connection = Application.connectToDatabase();
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, login);
                    statement.setString(2, hashPassword);
                    statement.setInt(3, 3);
                    statement.setInt(4, group);
                    statement.execute();

                    checkPassword.setTextFill(Paint.valueOf("GREEN"));
                    checkPassword.setText("You have successfully registered");
                    connection.close();
                } catch (SQLIntegrityConstraintViolationException e) {
                    checkPassword.setTextFill(Paint.valueOf("RED"));
                    checkPassword.setText("This login already exists");
                }
            }

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

