package com.github.comrada.forwarder.maintenance;

import com.github.comrada.forwarder.connector.Connector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;

public final class Reader {
    private static final Logger LOGGER = LogManager.getLogger();
    private final Connector connector;

    public Reader(Connector connector) {
        this.connector = connector;
        LOGGER.info("Reading connector is: {}", connector.getClass().getSimpleName());
    }

    Collection<String> read() {
        return connector.fetch();
    }
}
