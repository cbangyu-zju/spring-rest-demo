package com.spring.rest.handlers;


import com.spring.rest.models.ContentProfile;
import com.spring.rest.modules.ProfileManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

@Component
public class ContentProfileHandler{
    private static final Log LOG = LogFactory.getLog(ContentProfileHandler.class);
    private ProfileManager profileManager;

    @Autowired
    public ContentProfileHandler(ProfileManager profileManager){
        this.profileManager = profileManager;
    }

    @NotNull
    public Mono<ServerResponse> getContentProfile(ServerRequest request){
        try{
            String contentId = request.pathVariable("contentId");
            ContentProfile contentProfile = this.profileManager.getContentProfile(contentId);
            return ServerResponse.ok().contentType(APPLICATION_JSON_UTF8).body(fromObject(contentProfile));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(APPLICATION_JSON_UTF8)
                    .body(BodyInserters.fromObject(ex.toString()));
        }
    }
}