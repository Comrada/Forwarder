package com.github.comrada.forwarder.cache;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

/**
 * A cache that can do data preloading
 */
public class PreloadingCache implements PublishingCache<String> {
    private static final Logger LOGGER = LogManager.getLogger();
    private final PublishingCache<String> cache;
    private final Supplier<Collection<String>> dataSupplier;

    /**
     * Creates an instance of {@link PreloadingCache}.
     *
     * @param cache
     *         origin cache which we extend
     * @param dataSupplier
     *         data source where we load data
     */
    public PreloadingCache(PublishingCache<String> cache, Supplier<Collection<String>> dataSupplier) {
        this.cache = cache;
        this.dataSupplier = dataSupplier;
    }

    /** Initial cache warm up */
    public void warmUp() {
        try {
            dataSupplier.get().forEach(this::populate);
        } catch (Exception e) {
            LOGGER.error("Could not load data into the cache", e);
        }
    }

    @Override
    public void load(Collection<String> incomingData) {
        cache.load(incomingData);
    }

    @Override
    public List<String> populate(Collection<String> incomingData) {
        return cache.populate(incomingData);
    }

    @Override
    public String populate(String incomingData) {
        return cache.populate(incomingData);
    }

    @Override
    public String get(Object key) {
        return cache.get(key);
    }

    @Override
    public List<String> get(Set<Object> keys) {
        return cache.get(keys);
    }

    @Override
    public void cleanUp() {
        cache.cleanUp();
    }
}
