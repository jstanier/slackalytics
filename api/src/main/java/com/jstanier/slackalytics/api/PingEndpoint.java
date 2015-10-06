package com.jstanier.slackalytics.api;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Component
@Path("/ping")
public class PingEndpoint {

    @GET
    public String ping() {
        return "pong!";
    }
}
