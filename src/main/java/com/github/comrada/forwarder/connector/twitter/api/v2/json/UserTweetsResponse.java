package com.github.comrada.forwarder.connector.twitter.api.v2.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.comrada.forwarder.connector.twitter.api.ApiPath;

import java.util.List;

@ApiPath("/users/%s/tweets?tweet.fields=author_id,referenced_tweets,created_at&exclude=retweets,replies&max_results=%d")
public final class UserTweetsResponse {
    @JsonProperty
    private List<TweetData> data;

    public List<TweetData> getData() {
        return data;
    }
}
