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
        int tempUserGang;
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
                tempUserGang = resultSet.getInt("gang");
                tempDate = resultSet.getDate("date");
                quotes.addAll(new Quote(tempQuote, tempSubject, tempTeacher, tempDate, tempUserLogin, tempUserGang));
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
        String tempQuote;
        String tempTeacher;
        String tempSubject;
        String tempUserLogin;
        int tempUserGang;
        Date tempDate;

        connection = Application.connectToDatabase();
        PreparedStatement statement = null;
        String query = "";
        try {
            query = "SELECT status FROM user WHERE login = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, userLogin);
            ResultSet result = statement.executeQuery();
            int status = -1;
            if (result.next()) {
                status = result.getInt(1);
            }
            connection.close();
            connection = Application.connectToDatabase();
            query = "SELECT gang FROM user WHERE login = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, userLogin);
            ResultSet result2 = statement.executeQuery();
            int gang = -1;
            if (result2.next()) {
                gang = result2.getInt(1);
            }
            connection.close();
            connection = Application.connectToDatabase();
            switch (status) {
                case 3:
                    query = "SELECT * FROM teacher_quotes WHERE user_login = ?;";
                    statement = connection.prepareStatement(query);
                    statement.setString(1, userLogin);
                    break;
                case 2:
                    query = "SELECT * FROM teacher_quotes WHERE gang = ?;";
                    statement = connection.prepareStatement(query);
                    statement.setInt(1, gang);
                    break;
                case 1:
                    query = "SELECT * FROM teacher_quotes;";
                    statement = connection.prepareStatement(query);
                    break;
            }

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                tempQuote = resultSet.getString("quote");
                tempSubject = resultSet.getString("subject");
                tempTeacher = resultSet.getString("teacher");
                tempUserLogin = resultSet.getString("user_login");
                tempUserGang = resultSet.getInt("gang");
                tempDate = resultSet.getDate("date");
                quotes.addAll(new Quote(tempQuote, tempSubject, tempTeacher, tempDate, tempUserLogin, tempUserGang));
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
