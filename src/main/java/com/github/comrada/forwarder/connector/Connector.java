package com.github.comrada.forwarder.connector;

import java.util.Collection;

/**
 * A common interface for all connectors that can read data from some source.
 */
public interface Connector {

    /**
     * @return list of messages
     */
    Collection<String> fetch();
}
