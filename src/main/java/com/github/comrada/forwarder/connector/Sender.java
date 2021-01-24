package com.github.comrada.forwarder.connector;

import java.util.Collection;

/**
 * Common interface for all connectors that can send messages.
 */
public interface Sender extends AutoCloseable {

    /**
     * Send a collection of messages via a connector
     *
     * @param messages
     *         list of messages
     */
    void send(Collection<String> messages);

    /**
     * If the connector does not support closures, then you do not need to override
     *
     * @throws Exception
     *         if this resource cannot be closed
     */
    default void close () throws Exception {

    }
}
