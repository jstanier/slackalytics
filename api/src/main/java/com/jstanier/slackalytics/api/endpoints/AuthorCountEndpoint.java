package com.jstanier.slackalytics.api.endpoints;

import com.jstanier.slackalytics.api.constants.Constants;
import com.jstanier.slackalytics.api.exceptions.DateParsingException;
import com.jstanier.slackalytics.api.repository.AuthorCountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.util.Date;

@Controller
@Path("/authors")
public class AuthorCountEndpoint {

    @Autowired
    private AuthorCountsRepository repository;

    @GET
    @Path("/{channel}/counts")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAuthorCounts(@NotNull @PathParam("channel") String channel,
                                    @QueryParam("from") String from,
                                    @QueryParam("to") String to) {
        Date fromDate = null, toDate = null;
        if (from != null && to != null) {
            System.out.println(from + " " + to);
            try {
                fromDate = Constants.PARAMETER_DATE_FORMAT.parse(from);
                toDate = Constants.PARAMETER_DATE_FORMAT.parse(to);
            } catch (ParseException e) {
                throw new DateParsingException("Dates must be given the format " + Constants.DATE_FORMAT);
            }
        }

        System.out.println("From: " + fromDate + " To: " + toDate);
        return Response.ok(repository.getAuthorCountsByChannel(channel)).build();
    }
}
