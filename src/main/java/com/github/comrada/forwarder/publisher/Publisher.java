package com.github.comrada.forwarder.publisher;

import com.github.comrada.forwarder.connector.Sender;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;

public final class Publisher {
    private static final Logger LOGGER = LogManager.getLogger();
    private final Sender sender;

    public Publisher(Sender sender) {
        this.sender = sender;
        LOGGER.info("Sending connector is: {}", sender.getClass().getSimpleName());
    }

    void publish(Collection<String> messages) {
        sender.send(messages);
    }
}
