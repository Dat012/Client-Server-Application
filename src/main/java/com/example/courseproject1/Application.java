package com.example.courseproject1;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class Application extends javafx.application.Application {

    private static Stage currentStage;
    @Override
    public void start(Stage stage) throws IOException {
        currentStage = stage;
        stage.setResizable(false);
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("authorization.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 600);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void changeScene(String fxml) {
        try {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(Application.class.getResource(fxml)));
            currentStage.getScene().setRoot(parent);
        } catch (IOException e) {
            System.out.println(e + " Исключение в методе changeScene при загрузке FXML");
        }

    }

    public static ResultSet executeSQL(String query) throws SQLException {
        Connection connection = Application.connectToDatabase();
        Statement statement = connection.createStatement();
        ResultSet result = null;
        if (query.contains("SELECT")) {
            result = statement.executeQuery(query);
        } else {
            statement.execute(query);
        }

        connection.close();
        return result;
    }

    public static Connection connectToDatabase() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2047_courseproject",
                    "std_2047_courseproject", "courseproject1");
//
//            Statement statement = connection.createStatement();
//            String query = "SELECT * FROM user";
//            ResultSet result = statement.executeQuery(query);

//            String password_hash = result.getString("password_hash");


//
        }
        catch(Exception e){
            System.out.println(e);
        }
        return connection;
    }
}