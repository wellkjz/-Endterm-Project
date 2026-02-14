package com.berk.dragons.service;

import com.berk.dragons.dto.DragonRequestDto;
import com.berk.dragons.model.DragonBase;
import com.berk.dragons.patterns.DragonBuilder;
import com.berk.dragons.patterns.InMemoryCache;
import com.berk.dragons.patterns.SystemLogger;
import com.berk.dragons.repository.DragonRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DragonService {

    private final DragonRepository repository;

    private static final String CACHE_KEY_ALL_DRAGONS = "dragons:all";
    private static final Duration ALL_DRAGONS_TTL = Duration.ofMinutes(5);

    public DragonService(DragonRepository repository) {
        this.repository = repository;
    }

    public List<DragonBase> getAllDragons() {
        SystemLogger.getInstance().info("Fetching all dragons (with cache)");

        InMemoryCache cache = InMemoryCache.getInstance();

        Optional<ArrayList> cached = cache.get(CACHE_KEY_ALL_DRAGONS, ArrayList.class);
        if (cached.isPresent()) {
            SystemLogger.getInstance().info("CACHE HIT: " + CACHE_KEY_ALL_DRAGONS);
            @SuppressWarnings("unchecked")
            List<DragonBase> result = (List<DragonBase>) cached.get();
            return result;
        }

        SystemLogger.getInstance().info("CACHE MISS: " + CACHE_KEY_ALL_DRAGONS + " -> querying DB");
        List<DragonBase> result = repository.findAll();

        cache.put(CACHE_KEY_ALL_DRAGONS, new ArrayList<>(result), ALL_DRAGONS_TTL);

        return result;
    }

    public DragonBase getDragonById(int id) {
        return repository.findById(id);
    }

    public void createDragon(DragonRequestDto dto) {
        DragonBase dragon = new DragonBuilder()
                .setName(dto.getName())
                .setType(dto.getType())
                .setPrice(dto.getPrice())
                .build();

        repository.save(dragon);
        SystemLogger.getInstance().info("Created dragon: " + dto.getName());

        InMemoryCache.getInstance().invalidate(CACHE_KEY_ALL_DRAGONS);
    }

    public void updateDragon(int id, DragonRequestDto dto) {
        DragonBase existing = repository.findById(id);
        if (existing != null) {
            existing.setName(dto.getName());
            existing.setBasePrice(dto.getPrice());
            repository.update(existing);

            InMemoryCache.getInstance().invalidate(CACHE_KEY_ALL_DRAGONS);
        }
    }

    public void deleteDragon(int id) {
        repository.delete(id);

        InMemoryCache.getInstance().invalidate(CACHE_KEY_ALL_DRAGONS);
    }
}