package com.apocalipse.zumbi.service;

import com.apocalipse.zumbi.domain.*;
import com.apocalipse.zumbi.dto.TradeItem;
import com.apocalipse.zumbi.dto.TradeRequest;
import com.apocalipse.zumbi.repository.ResourceRepository;
import com.apocalipse.zumbi.repository.SurvivorRepository;
import com.apocalipse.zumbi.repository.SurvivorResourceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class InventoryService {
    private final SurvivorRepository survivorRepository;
    private final SurvivorResourceRepository survivorResourceRepository;
    private final ResourceRepository resourceRepository;

    public InventoryService(SurvivorRepository survivorRepository, SurvivorResourceRepository survivorResourceRepository, ResourceRepository resourceRepository) {
        this.survivorRepository = survivorRepository;
        this.survivorResourceRepository = survivorResourceRepository;
        this.resourceRepository = resourceRepository;
    }

    public List<SurvivorResource> listAll() {
        return survivorResourceRepository.findAll();
    }

    public List<SurvivorResource> listBySurvivor(Long survivorId) {
        Survivor survivor = survivorRepository.findById(survivorId).orElseThrow();
        return survivor.getRecursos();
    }

    @Transactional
    public void swap(TradeRequest request) {
        Survivor origin = survivorRepository.findById(request.getSobrevivente_origem()).orElseThrow();
        Survivor target = survivorRepository.findById(request.getSobrevivente_destino()).orElseThrow();

        if (Boolean.TRUE.equals(origin.getZumbi()) || Boolean.TRUE.equals(target.getZumbi())) {
            throw new IllegalArgumentException("Sobrevivente infectado nao pode trocar");
        }

        int pointsOrigin = calculatePoints(request.getItens_origem());
        int pointsTarget = calculatePoints(request.getItens_destino());
        if (pointsOrigin != pointsTarget) {
            throw new IllegalArgumentException("Troca invalida: pontos nao batem");
        }

        applyItems(origin, target, request.getItens_origem());
        applyItems(target, origin, request.getItens_destino());
    }

    private int calculatePoints(List<TradeItem> items) {
        Map<Long, Resource> resources = resourceRepository.findAllById(
                items.stream().map(TradeItem::getIdRecurso).collect(Collectors.toSet())
        ).stream().collect(Collectors.toMap(Resource::getId, r -> r));
        return items.stream()
                .mapToInt(i -> resources.getOrDefault(i.getIdRecurso(), new Resource()).getPontos() * i.getQuantidade())
                .sum();
    }

    private void applyItems(Survivor from, Survivor to, List<TradeItem> items) {
        for (TradeItem item : items) {
            SurvivorResource fromRes = findResource(from, item.getIdRecurso());
            if (fromRes.getQuantidade() < item.getQuantidade()) {
                throw new IllegalArgumentException("Quantidade insuficiente");
            }
            fromRes.setQuantidade(fromRes.getQuantidade() - item.getQuantidade());
            survivorResourceRepository.save(fromRes);

            SurvivorResource toRes = findResource(to, item.getIdRecurso());
            toRes.setQuantidade(toRes.getQuantidade() + item.getQuantidade());
            survivorResourceRepository.save(toRes);
        }
    }

    private SurvivorResource findResource(Survivor survivor, Long resourceId) {
        return survivor.getRecursos().stream()
                .filter(r -> r.getResource().getId().equals(resourceId))
                .findFirst()
                .orElseGet(() -> {
                    SurvivorResource sr = new SurvivorResource();
                    sr.setId(new SurvivorResourceId(survivor.getId(), resourceId));
                    sr.setSurvivor(survivor);
                    sr.setResource(resourceRepository.findById(resourceId).orElseThrow());
                    sr.setQuantidade(0);
                    survivor.getRecursos().add(sr);
                    return sr;
                });
    }
}
