package com.github.comrada.forwarder.reader;

import com.github.comrada.forwarder.connector.Connector;

import java.util.Collection;

public final class Reader {
    private final Connector connector;

    public Reader(Connector connector) {
        this.connector = connector;
    }

    Collection<String> read() {
        return connector.fetch();
    }
}
