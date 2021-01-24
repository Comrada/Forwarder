package com.github.comrada.forwarder.connector.twitter;

import com.github.comrada.forwarder.connector.Connector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.Collections;

public final class TwitterConnector implements Connector {
    private static final Logger LOGGER = LogManager.getLogger();
    private final TwitterProperties twitterProperties;

    public TwitterConnector(TwitterProperties twitterProperties) {
        this.twitterProperties = twitterProperties;
    }

    @Override
    public Collection<String> fetch() {
        LOGGER.info("I'm TwitterConnector and I'm reading messages...");
        return Collections.emptyList();
    }
}
