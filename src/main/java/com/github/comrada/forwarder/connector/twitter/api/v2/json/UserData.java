package com.github.comrada.forwarder.connector.twitter.api.v2.json;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public final class UserData {
    @JsonProperty
    private String id;
    @JsonProperty
    private String name;
    @JsonProperty
    private String username;
    @JsonProperty
    private Boolean verified;
    @JsonProperty("created_at")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private Instant createdAt;
    @JsonProperty
    private String location;
    @JsonProperty
    private String description;
    @JsonProperty
    private String url;
    @JsonProperty("profile_image_url")
    private String profileImageUrl;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public Boolean isVerified() {
        return verified;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }
}
