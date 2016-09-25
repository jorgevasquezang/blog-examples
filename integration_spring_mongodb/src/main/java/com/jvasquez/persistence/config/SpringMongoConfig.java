package com.jvasquez.persistence.config;

import com.mongodb.MongoClientURI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.net.UnknownHostException;

@Configuration
@EnableMongoRepositories(basePackages = {"com.jvasquez.persistence.*"})
public class SpringMongoConfig {

    private SimpleMongoDbFactory simpleMongoDbFactory() {

        SimpleMongoDbFactory simpleMongoDbFactory = null;
        MongoClientURI mongoClientURI = new MongoClientURI(getConnectionUri());

        try {
            simpleMongoDbFactory = new SimpleMongoDbFactory(mongoClientURI);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return simpleMongoDbFactory;
    }

    private String getConnectionUri() {
        return "mongodb://localhost:27017/test";
    }

    public
    @Bean
    MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(simpleMongoDbFactory());
    }
}