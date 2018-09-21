package com.spring.rest.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContentProfile{

    @JsonProperty("contentId")
    private String contentId;

    @JsonProperty("contentType")
    private String contentType;

    public ContentProfile(String contentId, String contentType){
        this.contentId = contentId;
        this.contentType = contentType;
    }

    public String getContentId() {
        return contentId;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}