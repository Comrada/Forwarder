package com.github.comrada.forwarder.connector.twitter.api.v2.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.comrada.forwarder.connector.twitter.api.ApiPath;

@ApiPath("/tweets/%s?tweet.fields=created_at")
public final class TweetResponse {
    @JsonProperty
    private TweetData data;

    public TweetData getData() {
        return data;
    }
}
