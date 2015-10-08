package com.jstanier.slackalytics.api.domain;

import java.util.Date;

public class ChannelCounts {

    private String channel;
    private Date date;
    private Long messages;

    public ChannelCounts(String channel, Date date, Long messages) {
        this.messages = messages;
        this.channel = channel;
        this.date = date;
    }

    public String getChannel() {
        return channel;
    }

    public Date getDate() {
        return date;
    }

    public Long getMessages() {
        return messages;
    }

}
