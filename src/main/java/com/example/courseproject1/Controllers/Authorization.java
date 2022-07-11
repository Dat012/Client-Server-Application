package com.example.courseproject1.Controllers;

import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;

import com.example.courseproject1.Application;
import com.example.courseproject1.CollectionQuotes;
import com.example.courseproject1.User;
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
            String login = loginField.getText();
            String hashPassword = User.makeHashStatic(passwordField.getText());

            if (Objects.equals(login, "") || Objects.equals(hashPassword, "")) {
                checkPassword.setTextFill(Paint.valueOf("RED"));
                checkPassword.setText("Please fill in all the fields");
            } else {
                connection = Application.connectToDatabase();
                String query = "SELECT * FROM user WHERE login = ? AND password_hash = ?;";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, login);
                statement.setString(2, hashPassword);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    int userId = resultSet.getInt("id");
                    int userStatus = resultSet.getInt("status");
                    int userGang = resultSet.getInt("gang");
                    CollectionQuotes userQuotes = new CollectionQuotes();
                    userQuotes.fillQuotes(login);
                    user = new User(userId, login, hashPassword, userStatus, userGang, userQuotes);
                    Application.user = user;
                    Application.allQuotes.fillQuotes();  // Получение всех цитат
                    //Application.user.getMyQuotes().fillQuotes(Application.user.getLogin()); // Получение цитат пользователя, который сейчас авторизуется
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
    void signInGuestButtonListener(ActionEvent event) {
        Application.changeScene("all_quotes.fxml");
        Application.user = new User(4);
    }

    @FXML
    void signUpButtonListener(ActionEvent event) {
        Application.changeScene("registration.fxml");
    }

    @FXML
    void checkAuthorizationData() {

    }

}
