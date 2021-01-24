package com.github.comrada.forwarder.connector.twitter.api;

import com.github.comrada.forwarder.connector.twitter.model.Tweet;
import com.github.comrada.forwarder.connector.twitter.model.User;

import java.util.Collection;

public interface ApiClient {

    User getUserById(String id);

    User getUserByName(String userName);

    Tweet getTweet(String id);

    Collection<Tweet> getLastUserTweets(String userId, int count);
}
