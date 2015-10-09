package com.jstanier.slackalytics.api.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jstanier.slackalytics.api.constants.Constants;

import java.util.Date;

public class AuthorCounts {

    private String channel;
    private String author;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern= Constants.DATE_FORMAT, timezone="UTC")
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