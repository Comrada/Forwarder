package com.github.comrada.forwarder.connector;

import java.util.Collection;

/**
 * Common interface for all connectors that can send messages.
 */
public interface Sender {

    /**
     * Send a collection of messages via a connector
     *
     * @param messages
     *         list of messages
     */
    void send(Collection<String> messages);
}
