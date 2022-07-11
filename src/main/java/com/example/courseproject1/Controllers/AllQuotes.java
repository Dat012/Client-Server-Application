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

import java.util.Date;

public class AllQuotes {
    private User user;

    private CollectionQuotes collectionQuotes = new CollectionQuotes();
    @FXML
    private Button accountButton;

    @FXML
    private Button backButton;

    @FXML
    private TableView<Quote> allQuotesTable;

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
    private void initialize() {
        quoteColumn.setCellValueFactory(new PropertyValueFactory<Quote, String>("content"));
        subjectColumn.setCellValueFactory(new PropertyValueFactory<Quote, String>("subject"));
        teacherColumn.setCellValueFactory(new PropertyValueFactory<Quote, String>("teacher"));
        userLoginColumn.setCellValueFactory(new PropertyValueFactory<Quote, String>("login"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Quote, Date>("date"));

        collectionQuotes.fillQuotes();

        allQuotesTable.setItems(collectionQuotes.getQuotes());
    }

    @FXML
    void accountButtonListener(ActionEvent event) {

    }

    @FXML
    void backButtonListener(ActionEvent event) {
        Application.changeScene("menu.fxml");
    }


}