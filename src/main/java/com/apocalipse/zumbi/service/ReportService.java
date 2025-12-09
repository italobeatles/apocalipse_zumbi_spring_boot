package com.apocalipse.zumbi.service;

import com.apocalipse.zumbi.repository.InfectionReportRepository;
import com.apocalipse.zumbi.repository.SurvivorRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ReportService {
    private final SurvivorRepository survivorRepository;
    private final InfectionReportRepository infectionReportRepository;

    public ReportService(SurvivorRepository survivorRepository, InfectionReportRepository infectionReportRepository) {
        this.survivorRepository = survivorRepository;
        this.infectionReportRepository = infectionReportRepository;
    }

    public Map<String, Object> generalReport() {
        long total = survivorRepository.count();
        long infected = infectionReportRepository.countBySurvivor_ZumbiTrue();
        long healthy = total - infected;
        Map<String, Object> result = new HashMap<>();
        result.put("total", total);
        result.put("infectados", total == 0 ? 0 : (double) infected / total);
        result.put("nao_infectados", total == 0 ? 0 : (double) healthy / total);
        return result;
    }
}
