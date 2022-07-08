package com.example.courseproject1;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
    private String login;
    private String hashPassword;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHashPassword() {
        return hashPassword;
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

}
