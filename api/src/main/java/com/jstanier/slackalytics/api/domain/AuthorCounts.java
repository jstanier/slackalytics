package com.jstanier.slackalytics.api.domain;

import java.util.Date;

public class AuthorCounts {

    private String channel;
    private String author;
    private Date date;
    private Long messages;

    public AuthorCounts(String channel, String author, Date date, Long messages) {
        this.channel = channel;
        this.author = author;
        this.date = date;
        this.messages = messages;
    }

    public String getChannel() {
        return channel;
    }

    public String getAuthor() {
        return author;
    }

    public Date getDate() {
        return date;
    }

    public Long getMessages() {
        return messages;
    }
}