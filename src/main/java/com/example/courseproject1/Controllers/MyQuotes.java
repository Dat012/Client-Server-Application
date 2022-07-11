package com.example.courseproject1.Controllers;

import com.example.courseproject1.Application;
import com.example.courseproject1.CollectionQuotes;
import com.example.courseproject1.Quote;
import com.example.courseproject1.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class MyQuotes {
    User user;

    EditQuote editQuoteController;

    Connection connection;

    @FXML
    private Button accountButton;

    @FXML
    private Button addButton;

    @FXML
    private Button backButton;

    @FXML
    private Button changeQuoteButton;

    @FXML
    private Button deleteQuoteButton;

    @FXML
    private TableView<Quote> myQuotesTable;

    @FXML
    private TableColumn<Quote, String> quoteColumn;

    @FXML
    private TableColumn<Quote, String> subjectColumn;

    @FXML
    private TableColumn<Quote, String> teacherColumn;

    @FXML
    private TableColumn<Quote, String> userLoginColumn;

    @FXML
    private TableColumn<Quote, Date> dateColumn;

    @FXML
    void accountButtonListener(ActionEvent event) {

    }

    @FXML
    void backButtonListener(ActionEvent event) {
        Application.changeScene("menu.fxml");
    }

    @FXML
    void addButtonListener(ActionEvent event) {
        Application.changeScene("add_quote.fxml");
    }

    @FXML
    void changeQuoteButtonListener(ActionEvent event) {
        Quote selectedQuote = myQuotesTable.getSelectionModel().getSelectedItem();
        user.setChangingQuote(selectedQuote);
        Application.changeScene("edit_quote.fxml");
    }

    @FXML
    void deleteQuoteButtonListener(ActionEvent event) {
        Quote selectedQuote = myQuotesTable.getSelectionModel().getSelectedItem();
        try {
            connection = Application.connectToDatabase();
            String query = "DELETE FROM teacher_quotes WHERE (quote = ?);";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, selectedQuote.getContent());
            statement.execute();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Application.user.getMyQuotes().remove(selectedQuote);
        Application.allQuotes.remove(selectedQuote);

    }

    @FXML
    private void initialize() {
        user = Application.user;

        quoteColumn.setCellValueFactory(new PropertyValueFactory<Quote, String>("content"));
        subjectColumn.setCellValueFactory(new PropertyValueFactory<Quote, String>("subject"));
        teacherColumn.setCellValueFactory(new PropertyValueFactory<Quote, String>("teacher"));
        userLoginColumn.setCellValueFactory(new PropertyValueFactory<Quote, String>("login"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Quote, Date>("date"));

        myQuotesTable.setItems(user.getMyQuotes().getQuotes());
    }

}