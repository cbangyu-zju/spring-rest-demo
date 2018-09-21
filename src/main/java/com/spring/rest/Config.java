package com.spring.rest;

import com.spring.rest.handlers.ContentProfileHandler;
import com.spring.rest.handlers.UserProfileHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;


@Configuration
@EnableWebFlux
public class Config {
    private static final Log LOG = LogFactory.getLog(Config.class);
    private final UserProfileHandler userProfileHandler;
    private final ContentProfileHandler contentProfileHandler;

    public Config(
            ContentProfileHandler contentProfileHandler,
            UserProfileHandler userProfileHandler
    ){
        this.contentProfileHandler = contentProfileHandler;
        this.userProfileHandler = userProfileHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
        }));
        return route(GET("/health"), serverRequest -> {
            LOG.debug("get health check request");
            return ServerResponse.ok().body(fromObject("I'm still alive")); })
                .and(route(GET("/user/{userId}/getUserProfile"), userProfileHandler::getUserProfile))
                .and(route(GET("/content/{contentId}/getContentProfile"), contentProfileHandler::getContentProfile));
    }
}