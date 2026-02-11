package com.berk.dragons.service;

import com.berk.dragons.dto.DragonRequestDto;
import com.berk.dragons.model.DragonBase;
import com.berk.dragons.patterns.DragonBuilder;
import com.berk.dragons.patterns.SystemLogger;
import com.berk.dragons.repository.DragonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DragonService {
    private final DragonRepository repository;

    public DragonService(DragonRepository repository) {
        this.repository = repository;
    }

    public List<DragonBase> getAllDragons() {
        SystemLogger.getInstance().info("Fetching all dragons");
        return repository.findAll();
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
    }

    public void updateDragon(int id, DragonRequestDto dto) {
        DragonBase existing = repository.findById(id);
        if (existing != null) {
            existing.setName(dto.getName());
            existing.setBasePrice(dto.getPrice());
            repository.update(existing);
        }
    }

    public void deleteDragon(int id) {
        repository.delete(id);
    }
}