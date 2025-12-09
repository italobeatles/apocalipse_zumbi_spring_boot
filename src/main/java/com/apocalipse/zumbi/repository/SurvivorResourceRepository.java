package com.apocalipse.zumbi.repository;

import com.apocalipse.zumbi.domain.SurvivorResource;
import com.apocalipse.zumbi.domain.SurvivorResourceId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurvivorResourceRepository extends JpaRepository<SurvivorResource, SurvivorResourceId> {
}
