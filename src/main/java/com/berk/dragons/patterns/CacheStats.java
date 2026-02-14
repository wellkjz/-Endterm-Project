package com.berk.dragons.patterns;

public class CacheStats {
    private final long hits;
    private final long misses;
    private final int size;

    public CacheStats(long hits, long misses, int size) {
        this.hits = hits;
        this.misses = misses;
        this.size = size;
    }

    public long getHits() { return hits; }
    public long getMisses() { return misses; }
    public int getSize() { return size; }
}