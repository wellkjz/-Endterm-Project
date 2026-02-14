package com.berk.dragons.patterns;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class InMemoryCache {

    private static volatile InMemoryCache instance;

    private final ConcurrentMap<String, CacheEntry> store = new ConcurrentHashMap<>();

    private InMemoryCache() {}

    public static InMemoryCache getInstance() {
        if (instance == null) {
            synchronized (InMemoryCache.class) {
                if (instance == null) {
                    instance = new InMemoryCache();
                }
            }
        }
        return instance;
    }

    public <T> Optional<T> get(String key, Class<T> type) {
        CacheEntry entry = store.get(key);
        if (entry == null) return Optional.empty();

        if (entry.isExpired()) {
            store.remove(key);
            return Optional.empty();
        }

        Object value = entry.value;
        if (!type.isInstance(value)) return Optional.empty();

        return Optional.of(type.cast(value));
    }

    public void put(String key, Object value, Duration ttl) {
        store.put(key, new CacheEntry(value, ttl));
    }

    public void invalidate(String key) {
        store.remove(key);
    }

    public void clear() {
        store.clear();
    }

    private static final class CacheEntry {
        private final Object value;
        private final Instant expiresAt; // null => never expires

        private CacheEntry(Object value, Duration ttl) {
            this.value = value;
            this.expiresAt = (ttl == null) ? null : Instant.now().plus(ttl);
        }

        private boolean isExpired() {
            return expiresAt != null && Instant.now().isAfter(expiresAt);
        }
    }
}