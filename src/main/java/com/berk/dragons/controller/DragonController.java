package com.berk.dragons.controller;

import com.berk.dragons.dto.DragonRequestDto;
import com.berk.dragons.model.DragonBase;
import com.berk.dragons.service.DragonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dragons")
public class DragonController {

    private final DragonService service;

    public DragonController(DragonService service) {
        this.service = service;
    }

    @GetMapping
    public List<DragonBase> getAll() {
        return service.getAllDragons();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DragonBase> getOne(@PathVariable int id) {
        DragonBase dragon = service.getDragonById(id);
        return dragon != null ? ResponseEntity.ok(dragon) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody DragonRequestDto dto) {
        service.createDragon(dto);
        return ResponseEntity.status(201).body("Dragon Created Successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody DragonRequestDto dto) {
        service.updateDragon(id, dto);
        return ResponseEntity.ok("Dragon Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.deleteDragon(id);
        return ResponseEntity.noContent().build();
    }
}