package com.jstanier.slackalytics.api.config;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;

@EnableAutoConfiguration
@Configuration
class Config {

    private Session session;

    @Bean
    public Session session() {
        if (session == null) {
            Cluster cassandra = Cluster.builder().addContactPoint("localhost").build();
            session = cassandra.connect("slackalytics");
        }
        return session;
    }

    @PreDestroy
    public void close() {
        session.close();
    }
}
