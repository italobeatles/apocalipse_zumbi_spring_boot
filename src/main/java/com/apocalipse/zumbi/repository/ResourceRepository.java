package com.apocalipse.zumbi.repository;

import com.apocalipse.zumbi.domain.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<Resource, Long> {
}
