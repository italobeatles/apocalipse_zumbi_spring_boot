package com.apocalipse.zumbi.repository;

import com.apocalipse.zumbi.domain.Survivor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurvivorRepository extends JpaRepository<Survivor, Long> {
}
