package com.berk.dragons.controller;

import com.berk.dragons.dto.RiderRequestDto;
import com.berk.dragons.dto.RiderResponseDto;
import com.berk.dragons.model.Rider;
import com.berk.dragons.service.RiderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/riders")
public class RiderController {

    private final RiderService service;

    public RiderController(RiderService service) {
        this.service = service;
    }

    @GetMapping
    public List<RiderResponseDto> getAll() {
        return service.getAllRiders()
                .stream()
                .map(RiderResponseDto::new)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RiderResponseDto> getOne(@PathVariable int id) {
        Rider rider = service.getRiderById(id);
        return rider != null
                ? ResponseEntity.ok(new RiderResponseDto(rider))
                : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody RiderRequestDto dto) {
        service.createRider(dto);
        return ResponseEntity.status(201).body("Rider Created Successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody RiderRequestDto dto) {
        service.updateRider(id, dto);
        return ResponseEntity.ok("Rider Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.deleteRider(id);
        return ResponseEntity.noContent().build();
    }


}