package com.github.comrada.forwarder.publisher;

import com.github.comrada.forwarder.connector.WritableConnector;

import java.util.Collection;

public final class Publisher {
    private final WritableConnector connector;

    public Publisher(WritableConnector connector) {
        this.connector = connector;
    }

    void publish(Collection<String> messages) {
        connector.write(messages);
    }
}
