package com.example.courseproject1.Controllers;

import com.example.courseproject1.Application;
import com.example.courseproject1.Quote;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;

import java.sql.*;
import java.time.LocalDate;

public class AddQuote {

    Connection connection;

    @FXML
    private Button addQuoteButton;

    @FXML
    private Button backButton;

    @FXML
    private Label checkFields;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField quoteContentField;

    @FXML
    private TextField subjectField;

    @FXML
    private TextField teacherField;

    @FXML
    void addQuoteButtonListener(ActionEvent event) {
        try {
            connection = Application.connectToDatabase();
            String quoteContent = quoteContentField.getText();
            String subject = subjectField.getText();
            String teacher = teacherField.getText();
            LocalDate date = datePicker.getValue();

            if (quoteContentField == null || subject == null || teacher == null || date == null) {
                checkFields.setTextFill(Paint.valueOf("RED"));
                checkFields.setText("Пожалуйста, заполните все поля");
            } else {
                String query = "INSERT INTO teacher_quotes(quote, subject, teacher, date, user_login, gang) VALUES (?, ?, ?, ?, ?, ?);";
                PreparedStatement statement = connection.prepareStatement(query);

                //statement.setInt(1, Application.user.getId());
                statement.setString(1, quoteContent);
                statement.setString(2, subject);
                statement.setString(3, teacher);
                statement.setDate(4, Date.valueOf(date));
                statement.setString(5, Application.user.getLogin());
                statement.setInt(6, Application.user.getGang());

                try {
                    statement.execute();
                    checkFields.setTextFill(Paint.valueOf("GREEN"));
                    checkFields.setText("Вы добавили цитату");
                    Application.user.getMyQuotes().add(new Quote(quoteContent, subject, teacher, Date.valueOf(date), Application.user.getLogin(), Application.user.getGang()));
                } catch (SQLIntegrityConstraintViolationException e) {
                    checkFields.setTextFill(Paint.valueOf("RED"));
                    checkFields.setText("Цитата не была добавлена :(");
                }
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void backButtonListener(ActionEvent event) {
        Application.changeScene("my_quotes.fxml");
    }

    @FXML
    void datePickerListener(ActionEvent event) {

    }

}
