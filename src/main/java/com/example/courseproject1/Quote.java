package com.example.courseproject1;

import java.util.Date;

public class Quote {
    private int id;

    private String content;
    private String subject;
    private String teacher;
    private Date date;
    private String login;

    private int gang;

    public Quote(String content, String subject, String teacher, Date date, String login, int gang) {
        //this.id = id;
        this.content = content;
        this.subject = subject;
        this.teacher = teacher;
        this.date = date;
        this.login = login;
        this.gang = gang;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getSubject() {
        return subject;
    }

    public String getTeacher() {
        return teacher;
    }

    public Date getDate() {
        return date;
    }

    public String getLogin() {
        return login;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", subject='" + subject + '\'' +
                ", teacher='" + teacher + '\'' +
                ", date=" + date +
                ", login='" + login + '\'' +
                '}';
    }
}
