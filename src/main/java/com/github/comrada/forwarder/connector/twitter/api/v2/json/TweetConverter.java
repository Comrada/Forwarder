package com.github.comrada.forwarder.connector.twitter.api.v2.json;

import com.github.comrada.forwarder.connector.twitter.model.Tweet;

public final class TweetConverter {

    private TweetConverter() {
    }

    public static Tweet convert(TweetData response) {
        Tweet tweet = new Tweet();
        tweet.setId(response.getId());
        tweet.setText(response.getText());
        tweet.setCreatedAt(response.getCreatedAt());
        return tweet;
    }
}
