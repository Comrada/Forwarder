package com.github.comrada.forwarder.cache;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.Set;

class HashCacheTest {

    private final PublishingCache<TestData> cache = new HashCache<>(Duration.of(2, ChronoUnit.DAYS));
    private final TestData ex1 = new TestData(Instant.parse("2020-03-09T00:00:00Z"), Instant.parse("2020-03-09T00:30:00Z"),
            Instant.parse("2020-03-09T00:05:00Z"), 100L);
    private final TestData ex2 = new TestData(Instant.parse("2020-03-09T00:30:00Z"), Instant.parse("2020-03-09T01:00:00Z"),
            Instant.parse("2020-03-09T00:10:00Z"), 200L);
    private final TestData ex3 = new TestData(Instant.parse("2020-03-09T00:01:00Z"), Instant.parse("2020-03-09T01:30:00Z"),
            Instant.parse("2020-03-09T00:15:00Z"), 300L);

    @BeforeEach
    void initialLoad() {
        cache.load(Set.of(ex1, ex2, ex3));
    }

    @Test
    void testInitialLoad() {
        assertEquals(ex1, cache.get(ex1));
        assertEquals(ex2, cache.get(ex2));
        assertEquals(ex3, cache.get(ex3));
    }

    @Test
    void testSizeOfPopulatedItems() {
        cache.cleanUp();
        List<TestData> cachedValues = cache.populate(Set.of(ex1, ex2, ex3));
        assertEquals(3, cachedValues.size());
    }

    @Test
    void testIfAddTheSameObject() {
        assertNull(cache.populate(ex3));
    }

    @Test
    void testIfAddUpdatedObject() {
        TestData ex3_2 = new TestData(Instant.parse("2020-03-09T00:01:00Z"), Instant.parse("2020-03-09T01:30:00Z"),
                Instant.parse("2020-03-09T00:20:00Z"), 400L);
        TestData newEx3 = cache.populate(ex3_2);
        assertEquals(400L, newEx3.getValue());
    }

    @Test
    void testSingleGet() {
        TestData cachedEx1 = cache.get(ex1);
        assertEquals(ex1, cachedEx1);
    }

    @Test
    void testMultipleGet() {
        List<TestData> cachedSet = cache.get(Set.of(ex1, ex2, ex3));
        assertTrue(cachedSet.contains(ex1));
        assertTrue(cachedSet.contains(ex2));
        assertTrue(cachedSet.contains(ex3));
    }

    @Test
    void testExpiration() throws InterruptedException {
        PublishingCache<TestData> cache = new HashCache<>(Duration.of(2, ChronoUnit.SECONDS));
        cache.load(Set.of(ex1, ex2, ex3));
        Thread.sleep(3000);
        assertNull(cache.get(ex1));
        assertNull(cache.get(ex2));
        assertNull(cache.get(ex3));
    }

    @Test
    void testCleanUp() {
        cache.cleanUp();
        assertNull(cache.get(ex1));
        assertNull(cache.get(ex2));
        assertNull(cache.get(ex3));
    }

    @AfterEach
    void cleanUp() {
        cache.cleanUp();
    }

    private static final class TestData {
        private Instant timeFrom;
        private Instant timeTo;
        private Instant timestamp;
        private long value;

        public TestData(Instant timeFrom, Instant timeTo, Instant timestamp, long value) {
            this.timeFrom = timeFrom;
            this.timeTo = timeTo;
            this.timestamp = timestamp;
            this.value = value;
        }

        public Instant getTimeFrom() {
            return timeFrom;
        }

        public void setTimeFrom(Instant timeFrom) {
            this.timeFrom = timeFrom;
        }

        public Instant getTimeTo() {
            return timeTo;
        }

        public void setTimeTo(Instant timeTo) {
            this.timeTo = timeTo;
        }

        public Instant getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(Instant timestamp) {
            this.timestamp = timestamp;
        }

        public long getValue() {
            return value;
        }

        public void setValue(long value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TestData testData = (TestData) o;
            return value == testData.value &&
                    timeFrom.equals(testData.timeFrom) &&
                    timeTo.equals(testData.timeTo) &&
                    timestamp.equals(testData.timestamp);
        }

        @Override
        public int hashCode() {
            return Objects.hash(timeFrom, timeTo, timestamp, value);
        }
    }
}