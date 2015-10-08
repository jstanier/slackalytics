package com.jstanier.slackalytics.api.endpoints;

import com.jstanier.slackalytics.api.repository.ChannelCountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Controller
@Path("/channels")
public class ChannelCountEndpoint {

    @Autowired
    private ChannelCountsRepository repository;

    @GET
    @Path("/{channel}/counts")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getChannelCount(@PathParam("channel") String channel) {
        return Response.ok(repository.getChannelCountsByChannel(channel)).build();
    }
}
