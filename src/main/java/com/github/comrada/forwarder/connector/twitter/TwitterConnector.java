package com.github.comrada.forwarder.connector.twitter;

import static java.util.Objects.requireNonNull;

import com.github.comrada.forwarder.connector.Connector;
import com.github.comrada.forwarder.connector.twitter.api.ApiClient;
import com.github.comrada.forwarder.connector.twitter.model.Tweet;
import com.github.comrada.forwarder.connector.twitter.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

public final class TwitterConnector implements Connector {
    private static final Logger LOGGER = LogManager.getLogger();
    private final TwitterProperties twitterProperties;
    private final ApiClient apiClient;
    private User user;

    public TwitterConnector(TwitterProperties twitterProperties, ApiClient apiClient) {
        this.twitterProperties = requireNonNull(twitterProperties);
        this.apiClient = requireNonNull(apiClient);
    }

    @Override
    public Collection<String> fetch() {
        return apiClient
                .getLastUserTweets(getUser().getId(), twitterProperties.getTweetsToRead())
                .stream()
                .sorted(Comparator.comparing(Tweet::getId))
                .map(Tweet::getText)
                .collect(Collectors.toList());
    }

    private User getUser() {
        if (user == null) {
            user = apiClient.getUserByName(twitterProperties.getUsername());
            LOGGER.info("User {} (@{}) was loaded.", user.getName(), user.getUsername());
        }
        return user;
    }
}
