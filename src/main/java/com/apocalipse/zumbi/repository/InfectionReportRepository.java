package com.apocalipse.zumbi.repository;

import com.apocalipse.zumbi.domain.InfectionReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfectionReportRepository extends JpaRepository<InfectionReport, Long> {
    long countBySurvivor_ZumbiTrue();
    long countBySurvivor_ZumbiFalse();
}
