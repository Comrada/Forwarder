package com.github.comrada.forwarder.cache;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * The publication cache is a simple caching mechanism that protects data consumers from receiving old duplicate data.
 *
 * @param <T>
 *         type of cached data.
 */
public interface PublishingCache<T> {

    /**
     * Initial data load
     *
     * @param incomingData
     *         list of data to be cached
     */
    default void load(Collection<T> incomingData) {
    }

    /**
     * Populates the cache with new incoming data.
     *
     * @param incomingData
     *         list of data to be cached
     * @return a set of really new data that was not in the cache.
     */
    List<T> populate(Collection<T> incomingData);

    /**
     * Populates the cache with new single incoming object.
     *
     * @param incomingData
     *         data object to be cached
     * @return the same object, if it was not in the cache, {@code null} otherwise.
     */
    T populate(T incomingData);

    /**
     * Retrieves an object from the cache.
     *
     * @return returns an object from the cache, if there is one, {@code null} otherwise.
     */
    T get(Object key);

    /**
     * Retrieves from the cache a list of objects by their keys, if they are there.
     *
     * @param keys
     *         keys by which to get objects
     * @return list of cached objects.
     */
    List<T> get(Set<Object> keys);

    /**
     * Clears the cache. You may not override if not required.
     */
    default void cleanUp() {
    }
}
