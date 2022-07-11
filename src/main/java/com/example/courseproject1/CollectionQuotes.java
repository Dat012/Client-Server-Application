package com.example.courseproject1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.Collections;

public class CollectionQuotes {
    private Connection connection;

    private ObservableList<Quote> quotes = FXCollections.observableArrayList();

    public void add(Quote quote) {
        quotes.add(quote);
    }

    public void remove(Quote quote) {
        quotes.remove(quote);
    }

    public boolean updateQuote(Quote oldQuote, Quote newQuote) {
        for (int i = 0; i < quotes.size(); i++) {
            if (quotes.get(i).equals(oldQuote)) {
                return Collections.replaceAll(quotes, quotes.get(i), newQuote);
            }
        }
        return false;
    }

    public void fillQuotes() {
        String tempQuote;
        String tempTeacher;
        String tempSubject;
        String tempUserLogin;
        Date tempDate;

        connection = Application.connectToDatabase();
        String query = "SELECT * FROM teacher_quotes";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tempQuote = resultSet.getString("quote");
                tempSubject = resultSet.getString("subject");
                tempTeacher = resultSet.getString("teacher");
                tempUserLogin = resultSet.getString("user_login");
                tempDate = resultSet.getDate("date");
                quotes.addAll(new Quote(tempQuote, tempSubject, tempTeacher, tempDate, tempUserLogin));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void fillQuotes(String userLogin) {
        int tempId;
        String tempQuote;
        String tempTeacher;
        String tempSubject;
        String tempUserLogin;
        Date tempDate;

        connection = Application.connectToDatabase();
        String query = "SELECT * FROM teacher_quotes WHERE user_login = ?;";//, userLogin);

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, userLogin);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tempQuote = resultSet.getString("quote");
                tempSubject = resultSet.getString("subject");
                tempTeacher = resultSet.getString("teacher");
                tempUserLogin = resultSet.getString("user_login");
                tempDate = resultSet.getDate("date");
                quotes.addAll(new Quote(tempQuote, tempSubject, tempTeacher, tempDate, tempUserLogin));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "CollectionQuotes{" +
                "quotes=" + quotes +
                '}';
    }

    public ObservableList<Quote> getQuotes() {
        return quotes;
    }
}
