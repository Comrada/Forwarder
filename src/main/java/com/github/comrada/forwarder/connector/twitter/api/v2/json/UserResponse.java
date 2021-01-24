package com.github.comrada.forwarder.connector.twitter.api.v2.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class UserResponse {
    @JsonProperty
    private UserData data;

    public UserData getData() {
        return data;
    }
}
