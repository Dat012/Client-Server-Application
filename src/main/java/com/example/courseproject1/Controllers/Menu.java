package com.example.courseproject1.Controllers;

import com.example.courseproject1.Application;
import com.example.courseproject1.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Menu {

    @FXML
    private Button QuotesButton;

    @FXML
    private Button accountButton;

    @FXML
    private Button createQuoteButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button myQuotesButton;

    @FXML
    void QuotesButtonListener(ActionEvent event) {
        Application.changeScene("all_quotes.fxml");
    }

    @FXML
    void accountButtonListener(ActionEvent event) {
        Application.changeScene("account.fxml");
    }



    @FXML
    void exitButtonListener(ActionEvent event) {
        Application.changeScene("authorization.fxml");
        Application.user = null;
    }

    @FXML
    void myQuotesButtonListener(ActionEvent event) {
        Application.changeScene("my_quotes.fxml");
    }

}
