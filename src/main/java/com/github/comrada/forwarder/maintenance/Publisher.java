package com.github.comrada.forwarder.maintenance;

import com.github.comrada.forwarder.cache.PublishingCache;
import com.github.comrada.forwarder.connector.Sender;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;

public final class Publisher {
    private static final Logger LOGGER = LogManager.getLogger();
    private final Sender sender;
    private final PublishingCache<String> cache;

    public Publisher(Sender sender, PublishingCache<String> cache) {
        this.sender = sender;
        this.cache = cache;
        LOGGER.info("Sending connector is: {}", sender.getClass().getSimpleName());
    }

    void publish(Collection<String> messages) {
        Collection<String> newValues = cache.populate(messages);
        if (!newValues.isEmpty()) {
            sender.send(newValues);
        }
    }
}
