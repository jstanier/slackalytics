package com.jstanier.slackalytics.api.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class DateParsingException extends WebApplicationException {

    public DateParsingException(String message) {
        super(Response.status(Response.Status.BAD_REQUEST)
                .entity(message).type(MediaType.TEXT_PLAIN_TYPE).build());
    }
}
