package com.spring.rest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;

/**
 * Created by cbangyu on 2017/4/24.
 */
@SpringBootApplication(exclude = {
        MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class Server {
    private static final Log LOG = LogFactory.getLog(Server.class);

    /** This is necessary. The zipkin library will modify this at run time and send span information to zipkin service. */
    @Bean
    @Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        LOG.info(String.format("env: %s", System.getenv()));
        SpringApplication.run(Server.class, args);
    }
}