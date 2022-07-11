package com.example.courseproject1;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {

    private int id;
    private String login;
    private String hashPassword;

    private int status;

    private int gang;

    private CollectionQuotes myQuotes;

    private Quote changingQuote;

    public User(int id, String login, String hashPassword, int status, int gang, CollectionQuotes myQuotes) {
        this.id = id;
        this.login = login;
        this.hashPassword = hashPassword;
        this.status = status;
        this.gang = gang;
        this.myQuotes = myQuotes;
    }

    public String makeHash(String password) {
        hashPassword = "";
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(password.getBytes());
            for (byte b : bytes) {
                hashPassword += String.format("%02X", b);
            }
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Нет такого алгоритма шифрования");
        }
        //System.out.println(hashPassword);
        return hashPassword;
    }

    public static String makeHashStatic(String password) {
        String hashPassword = "";
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(password.getBytes());
            for (byte b : bytes) {
                hashPassword += String.format("%02X", b);
            }
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Нет такого алгоритма шифрования");
        }
        //System.out.println(hashPassword);
        return hashPassword;
    }

    public User() {
    }

    public User(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", hashPassword='" + hashPassword + '\'' +
                ", status=" + status +
                ", gang=" + gang +
                ", myQuotes=" + myQuotes +
                '}';
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Quote getChangingQuote() {
        return changingQuote;
    }

    public void setChangingQuote(Quote changingQuote) {
        this.changingQuote = changingQuote;
    }

    public int getStatus() {
        return status;
    }

    public int getGang() {
        return gang;
    }

    public CollectionQuotes getMyQuotes() {
        return myQuotes;
    }

    public String getLogin() {
        return login;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public int getId() {
        return id;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setGang(int gang) {
        this.gang = gang;
    }

    public void setMyQuotes(CollectionQuotes myQuotes) {
        this.myQuotes = myQuotes;
    }
}
