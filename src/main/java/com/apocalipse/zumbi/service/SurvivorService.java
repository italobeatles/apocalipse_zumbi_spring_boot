package com.apocalipse.zumbi.service;

import com.apocalipse.zumbi.domain.Survivor;
import com.apocalipse.zumbi.repository.SurvivorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SurvivorService {
    private final SurvivorRepository survivorRepository;

    public SurvivorService(SurvivorRepository survivorRepository) {
        this.survivorRepository = survivorRepository;
    }

    public List<Survivor> findAll() {
        return survivorRepository.findAll();
    }

    public Survivor findById(Long id) {
        return survivorRepository.findById(id).orElseThrow();
    }

    @Transactional
    public Survivor create(Survivor survivor) {
        return survivorRepository.save(survivor);
    }

    @Transactional
    public Survivor updateLocation(Long id, Double latitude, Double longitude) {
        Survivor survivor = findById(id);
        survivor.setLatitude(latitude);
        survivor.setLongitude(longitude);
        return survivorRepository.save(survivor);
    }

    @Transactional
    public void delete(Long id) {
        survivorRepository.deleteById(id);
    }
}
