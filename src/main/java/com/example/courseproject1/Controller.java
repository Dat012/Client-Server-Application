package com.example.courseproject1;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button authSignInButton;

    @FXML
    private TextField loginField;

    @FXML
    private Button loginSignUpButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    void initialize() {
        authSignInButton.setOnAction(actionEvent -> {
            System.out.println("rb");
        });
    }

    public static void setConnect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
//
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2047_courseproject",
                    "std_2047_courseproject", "courseproject1");
//
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM user";
            ResultSet result = statement.executeQuery(query);
//
            while(result.next()){
                int id = result.getInt("id");
                String login = result.getString("login");
                String password_hash = result.getString("password_hash");
                System.out.print("User: ");
                System.out.print("id = " + id);
                System.out.print(", login = \"" + login + "\"");
                System.out.println(", short name = \"" + password_hash + "\".");
            }
//
            connection.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

}
