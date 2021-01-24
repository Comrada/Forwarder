package com.github.comrada.forwarder.connector;

import java.util.Collection;

/**
 * A common interface for all connectors that can read data from some source.
 */
public interface Connector extends AutoCloseable {

    /**
     * @return list of messages
     */
    Collection<String> fetch();

    /**
     * If the connector does not support closures, then you do not need to override
     *
     * @throws Exception
     *         if this resource cannot be closed
     */
    default void close() throws Exception {

    }
}
