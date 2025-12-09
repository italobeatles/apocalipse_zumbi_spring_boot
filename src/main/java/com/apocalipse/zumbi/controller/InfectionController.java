package com.apocalipse.zumbi.controller;

import com.apocalipse.zumbi.domain.InfectionReport;
import com.apocalipse.zumbi.domain.Survivor;
import com.apocalipse.zumbi.repository.InfectionReportRepository;
import com.apocalipse.zumbi.repository.SurvivorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/informar-zumbificacao")
public class InfectionController {
    private final InfectionReportRepository infectionReportRepository;
    private final SurvivorRepository survivorRepository;

    public InfectionController(InfectionReportRepository infectionReportRepository, SurvivorRepository survivorRepository) {
        this.infectionReportRepository = infectionReportRepository;
        this.survivorRepository = survivorRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public InfectionReport inform(@RequestBody Map<String, Long> body) {
        Long survivorId = body.get("id_sobrevivente");
        Long informantId = body.get("id_informante");
        Survivor survivor = survivorRepository.findById(survivorId).orElseThrow();
        Survivor informant = survivorRepository.findById(informantId).orElseThrow();
        survivor.setZumbi(true);
        survivorRepository.save(survivor);
        InfectionReport report = new InfectionReport();
        report.setSurvivor(survivor);
        report.setInformant(informant);
        return infectionReportRepository.save(report);
    }
}
