package com.jstanier.slackalytics.api.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jstanier.slackalytics.api.constants.Constants;

import java.util.Date;

public class ChannelCounts {

    private String channel;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern= Constants.DATE_FORMAT, timezone="UTC")
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
