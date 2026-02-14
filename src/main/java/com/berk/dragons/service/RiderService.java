package com.berk.dragons.service;

import com.berk.dragons.dto.RiderRequestDto;
import com.berk.dragons.exception.InvalidInputException;
import com.berk.dragons.model.Rider;
import com.berk.dragons.patterns.InMemoryCache;
import com.berk.dragons.patterns.SystemLogger;
import com.berk.dragons.repository.RiderRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class RiderService {

    private final RiderRepository repository;

    private static final String CACHE_KEY_ALL_RIDERS = "riders:all";
    private static final Duration ALL_RIDERS_TTL = Duration.ofMinutes(5);

    public RiderService(RiderRepository repository) {
        this.repository = repository;
    }


    public List<Rider> getAllRiders() {
        SystemLogger.getInstance().info("Fetching all riders (with cache)");

        InMemoryCache cache = InMemoryCache.getInstance();

        @SuppressWarnings("unchecked")
        var cached = cache.get(CACHE_KEY_ALL_RIDERS, List.class);

        if (cached.isPresent()) {
            SystemLogger.getInstance().info("CACHE HIT: " + CACHE_KEY_ALL_RIDERS);
            return (List<Rider>) cached.get();
        }

        SystemLogger.getInstance().info("CACHE MISS: " + CACHE_KEY_ALL_RIDERS + " -> querying DB");

        List<Rider> result = repository.findAll();
        cache.put(CACHE_KEY_ALL_RIDERS, result, ALL_RIDERS_TTL);

        return result;
    }


    public Rider getRiderById(int id) {
        return repository.findById(id);
    }

    public void createRider(RiderRequestDto dto) {
        validate(dto);

        Rider rider = new Rider(0, dto.getName().trim(), dto.getSkillLevel());
        repository.save(rider);

        SystemLogger.getInstance().info("Created rider: " + dto.getName());

        InMemoryCache.getInstance().invalidate(CACHE_KEY_ALL_RIDERS);
    }


    public void updateRider(int id, RiderRequestDto dto) {
        validate(dto);

        Rider existing = repository.findById(id);

        if (existing != null) {
            Rider updated = new Rider(id, dto.getName().trim(), dto.getSkillLevel());
            repository.update(updated);

            SystemLogger.getInstance().info("Updated rider id=" + id);

            InMemoryCache.getInstance().invalidate(CACHE_KEY_ALL_RIDERS);
        }
    }

    public void deleteRider(int id) {
        repository.delete(id);

        SystemLogger.getInstance().info("Deleted rider id=" + id);

        InMemoryCache.getInstance().invalidate(CACHE_KEY_ALL_RIDERS);
    }

    private void validate(RiderRequestDto dto) {
        if (dto == null)
            throw new InvalidInputException("Request body cannot be null.");

        if (dto.getName() == null || dto.getName().trim().isEmpty())
            throw new InvalidInputException("Rider name cannot be empty.");

        if (dto.getSkillLevel() < 1 || dto.getSkillLevel() > 10)
            throw new InvalidInputException("Skill level must be between 1 and 10.");
    }
}