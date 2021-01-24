package com.github.comrada.forwarder.cache;

import static com.google.common.cache.CacheBuilder.newBuilder;
import static java.util.Objects.isNull;
import static java.util.stream.Collectors.toMap;

import com.google.common.cache.Cache;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Simple implementation based on Google Guava, provides an expiring key/value storage where a key is the object itself.
 * You must implement the correct hashCode() and equals() methods to make it work correctly.
 *
 * @param <T>
 *         type of cached data.
 */
public class HashCache<T> implements PublishingCache<T> {
    /** The cached data */
    private final Cache<Object, T> cache;

    /**
     * Creates an instance of {@link HashCache}.
     *
     * @param duration
     *         the length of time after an entry is created that it should be automatically removed.
     */
    public HashCache(Duration duration) {
        cache = newBuilder().expireAfterWrite(duration).build();
    }

    @Override
    public void load(Collection<T> incomingData) {
        cache.putAll(incomingData.stream().collect(toMap(i -> i, Function.identity())));
    }

    @Override
    public List<T> populate(Collection<T> incomingData) {
        List<T> newInfos = new ArrayList<>(incomingData.size());
        incomingData.forEach(incomingObject -> {
            if (!isNull(incomingObject) && isNull(cache.getIfPresent(incomingObject))) {
                newInfos.add(incomingObject);
                cache.put(incomingObject, incomingObject);
            }
        });
        return newInfos;
    }

    @Override
    public T populate(T incomingData) {
        if (!isNull(incomingData) && isNull(cache.getIfPresent(incomingData))) {
            cache.put(incomingData, incomingData);
            return incomingData;
        }
        return null;
    }

    @Override
    public T get(Object key) {
        return cache.getIfPresent(key);
    }

    @Override
    public List<T> get(Set<Object> keys) {
        return cache.getAllPresent(keys)
                .values()
                .stream()
                .filter(keys::contains)
                .collect(Collectors.toList());
    }

    @Override
    public void cleanUp() {
        cache.invalidateAll();
    }
}
