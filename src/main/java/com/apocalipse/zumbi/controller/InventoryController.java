package com.apocalipse.zumbi.controller;

import com.apocalipse.zumbi.domain.SurvivorResource;
import com.apocalipse.zumbi.dto.TradeRequest;
import com.apocalipse.zumbi.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventario")
public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public List<SurvivorResource> index() {
        return inventoryService.listAll();
    }

    @GetMapping("/{id}")
    public List<SurvivorResource> show(@PathVariable Long id) {
        return inventoryService.listBySurvivor(id);
    }

    @PostMapping("/troca")
    @ResponseStatus(HttpStatus.OK)
    public void trade(@RequestBody TradeRequest payload) {
        inventoryService.swap(payload);
    }
}
