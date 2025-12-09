package com.apocalipse.zumbi.service;

import com.apocalipse.zumbi.domain.Survivor;
import com.apocalipse.zumbi.domain.SurvivorResource;
import com.apocalipse.zumbi.domain.SurvivorResourceId;
import com.apocalipse.zumbi.repository.SurvivorRepository;
import com.apocalipse.zumbi.repository.SurvivorResourceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventoryService {
    private final SurvivorRepository survivorRepository;
    private final SurvivorResourceRepository survivorResourceRepository;

    public InventoryService(SurvivorRepository survivorRepository, SurvivorResourceRepository survivorResourceRepository) {
        this.survivorRepository = survivorRepository;
        this.survivorResourceRepository = survivorResourceRepository;
    }

    public List<SurvivorResource> listAll() {
        return survivorResourceRepository.findAll();
    }

    public List<SurvivorResource> listBySurvivor(Long survivorId) {
        Survivor survivor = survivorRepository.findById(survivorId).orElseThrow();
        return survivor.getRecursos();
    }

    @Transactional
    public void swap(Long origin, Long target, List<SurvivorResource> fromOrigin, List<SurvivorResource> fromTarget) {
        // Stub: In production you would validate points and transactional updates
        for (SurvivorResource item : fromOrigin) {
            item.setId(new SurvivorResourceId(origin, item.getResource().getId()));
            survivorResourceRepository.save(item);
        }
        for (SurvivorResource item : fromTarget) {
            item.setId(new SurvivorResourceId(target, item.getResource().getId()));
            survivorResourceRepository.save(item);
        }
    }
}
