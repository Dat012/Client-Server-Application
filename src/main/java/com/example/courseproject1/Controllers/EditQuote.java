package com.example.courseproject1.Controllers;

import com.example.courseproject1.Application;
import com.example.courseproject1.Quote;
import com.example.courseproject1.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;
import java.util.Date;

public class EditQuote {
    User user;

    Connection connection;
    @FXML
    private Button backButton;

    @FXML
    private Button changeQuoteButton;

    Quote changingQuote;
    String oldQuoteContent;

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
    void backButtonListener(ActionEvent event) {
        Application.changeScene("my_quotes.fxml");
    }

    @FXML
    void changeQuoteButtonListener(ActionEvent event) {
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
                String query = "UPDATE teacher_quotes SET quote = ?, teacher = ?, subject = ?, date = ? WHERE quote = ?;";
                PreparedStatement statement = connection.prepareStatement(query);

                //statement.setInt(1, Application.user.getId());
                statement.setString(1, quoteContent);
                statement.setString(2, teacher);
                statement.setString(3, subject);
                statement.setDate(4, java.sql.Date.valueOf(date));
                statement.setString(5, oldQuoteContent);

                try {
                    statement.execute();
                    checkFields.setTextFill(Paint.valueOf("GREEN"));
                    checkFields.setText("Вы изменили цитату");
                    Application.user.getMyQuotes().updateQuote(changingQuote, new Quote(quoteContent, subject, teacher, java.sql.Date.valueOf(date), Application.user.getLogin()));
                } catch (SQLIntegrityConstraintViolationException e) {
                    e.printStackTrace();
                    checkFields.setTextFill(Paint.valueOf("RED"));
                    checkFields.setText("Цитата не была изменена :(");
                }
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void datePickerListener(ActionEvent event) {

    }


    @FXML
    public void initialize() {
        user = Application.user;
        changingQuote = user.getChangingQuote();
        oldQuoteContent = changingQuote.getContent();
        quoteContentField.setText(changingQuote.getContent());
        subjectField.setText(changingQuote.getSubject());
        teacherField.setText(changingQuote.getTeacher());
        datePicker.setValue(LocalDate.of(Integer.parseInt(changingQuote.getDate().toString().substring(0, 4)), Integer.parseInt(changingQuote.getDate().toString().substring(5, 7)), Integer.parseInt(changingQuote.getDate().toString().substring(8, 10))));
    }
}
