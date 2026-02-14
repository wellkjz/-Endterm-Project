package com.berk.dragons.patterns;

import java.time.Duration;
import java.util.Optional;
import java.util.function.Supplier;

public interface CacheService {
    <T> Optional<T> get(String key, Class<T> type);
    void put(String key, Object value, Duration ttl);
    void invalidate(String key);
    void clear();

    <T> T getOrLoad(String key, Class<T> type, Duration ttl, Supplier<T> loader);

    CacheStats stats();
}