package com.github.comrada.forwarder.connector.twitter;

import com.github.comrada.forwarder.config.ReaderProperties;
import com.github.comrada.forwarder.connector.Connector;

import java.util.Collection;
import java.util.Collections;

public final class TwitterConnector implements Connector {

    private final ReaderProperties.Twitter twitterProperties;

    public TwitterConnector(ReaderProperties.Twitter twitterProperties) {
        this.twitterProperties = twitterProperties;
    }

    @Override
    public Collection<String> fetch() {
        //TODO
        return Collections.emptyList();
    }
}
