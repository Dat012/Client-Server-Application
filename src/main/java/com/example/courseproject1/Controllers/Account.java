package com.example.courseproject1.Controllers;

import com.example.courseproject1.Application;
import com.example.courseproject1.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Account {
    User user = new User();
    Connection connection;
    @FXML
    private Button backButton;

    @FXML
    private Button changeDataButton;

    @FXML
    private Label checkFields;

    @FXML
    private TextField newLoginField;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private TextField oldLoginField;

    @FXML
    private TextField oldPasswordField;

    @FXML
    void backButtonListener(ActionEvent event) {
        Application.changeScene("menu.fxml");
    }

    @FXML
    void changeDataButtonListener(ActionEvent event) {
        String oldLogin = oldLoginField.getText();
        String newLogin = newLoginField.getText();
        String oldPassword = oldPasswordField.getText();
        String newPassword = newPasswordField.getText();
        String query = "";

        try {
            if (Application.user.getLogin().equals(newLogin) || Application.user.getHashPassword().equals(User.makeHashStatic(newPassword))) { // Если пользователь ввел данные, которые совпадают с его текущими
                checkFields.setTextFill(Paint.valueOf("RED"));
                checkFields.setText("Duplicated data");
            } else {  // Если введенные пользователем данные не совпадают с его текущими
                connection = Application.connectToDatabase();
                PreparedStatement statement = null;
                if (oldLogin.length() * newLogin.length() > 0 && oldLogin.equals(Application.user.getLogin()) &&
                        oldPassword.length() * newPassword.length() > 0 && User.makeHashStatic(oldPassword).equals(Application.user.getHashPassword())) {  // Логин и пароль

                    query = "UPDATE user SET login = ?, password_hash = ? WHERE login = ?;"; //, newLogin, User.makeHashStatic(newPassword), oldLogin);

                    statement = connection.prepareStatement(query);
                    statement.setString(1, newLogin);
                    statement.setString(2, User.makeHashStatic(newPassword));
                    statement.setString(3, oldLogin);

                    Application.user.setLogin(newLogin);
                    Application.user.setHashPassword(User.makeHashStatic(newPassword));
                } else if (oldLogin.length() * newLogin.length() > 0 && oldLogin.equals(Application.user.getLogin())) {                                // Логин
                    query = "UPDATE user SET login = ? WHERE login = ?;"; //, newLogin, oldLogin);

                    statement = connection.prepareStatement(query);
                    statement.setString(1, newLogin);
                    statement.setString(2, oldLogin);

                    Application.user.setLogin(newLogin);
                }
                else if (oldPassword.length() * newPassword.length() > 0 && User.makeHashStatic(oldPassword).equals(Application.user.getHashPassword())) {  // Пароль
                    query = "UPDATE user SET password_hash = ? WHERE login = ?;"; //, User.makeHashStatic(newPassword), Application.user.getLogin());

                    statement = connection.prepareStatement(query);
                    statement.setString(1, User.makeHashStatic(newPassword));
                    statement.setString(2, Application.user.getLogin());

                    Application.user.setHashPassword(User.makeHashStatic(newPassword));
                }
                if (query.equals("")){
                    checkFields.setTextFill(Paint.valueOf("RED"));
                    checkFields.setText("Wrong data or fields are not filled");
                } else {
                    try {
                        statement.execute();
                        checkFields.setTextFill(Paint.valueOf("GREEN"));
                        checkFields.setText("Data saved");
                        connection.close();
                    } catch (SQLException e) {
                        checkFields.setTextFill(Paint.valueOf("RED"));
                        checkFields.setText("This login already exists");
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
