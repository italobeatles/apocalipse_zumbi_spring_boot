package com.apocalipse.zumbi.controller;

import com.apocalipse.zumbi.domain.Survivor;
import com.apocalipse.zumbi.service.SurvivorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sobreviventes")
public class SurvivorController {
    private final SurvivorService survivorService;

    public SurvivorController(SurvivorService survivorService) {
        this.survivorService = survivorService;
    }

    @GetMapping
    public List<Survivor> index() {
        return survivorService.findAll();
    }

    @GetMapping("/{id}")
    public Survivor show(@PathVariable Long id) {
        return survivorService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Survivor store(@Valid @RequestBody Survivor survivor) {
        return survivorService.create(survivor);
    }

    @PutMapping("/{id}")
    public Survivor updateLocation(@PathVariable Long id, @RequestBody Map<String, Double> body) {
        return survivorService.updateLocation(id, body.get("latitude"), body.get("longitude"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        survivorService.delete(id);
    }
}
