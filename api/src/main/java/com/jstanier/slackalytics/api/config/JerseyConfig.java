package com.jstanier.slackalytics.api.config;

import com.jstanier.slackalytics.api.endpoints.AuthorCountEndpoint;
import com.jstanier.slackalytics.api.endpoints.ChannelCountEndpoint;
import com.jstanier.slackalytics.api.endpoints.PingEndpoint;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        registerEndpoints();
    }

    private void registerEndpoints() {
        register(PingEndpoint.class);
        register(AuthorCountEndpoint.class);
        register(ChannelCountEndpoint.class);
    }

}
