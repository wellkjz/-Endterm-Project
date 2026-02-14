package com.berk.dragons.controller;

import com.berk.dragons.patterns.InMemoryCache;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cache")
public class CacheController {

    @DeleteMapping
    public ResponseEntity<String> clearAll() {
        InMemoryCache.getInstance().clear();
        return ResponseEntity.ok("Cache cleared");
    }

    @DeleteMapping("/{key}")
    public ResponseEntity<String> clearKey(@PathVariable String key) {
        InMemoryCache.getInstance().invalidate(key);
        return ResponseEntity.ok("Cache key invalidated: " + key);
    }
}