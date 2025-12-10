package com.apocalipse.zumbi.controller;

import com.apocalipse.zumbi.domain.Resource;
import com.apocalipse.zumbi.domain.Survivor;
import com.apocalipse.zumbi.domain.SurvivorResource;
import com.apocalipse.zumbi.repository.ResourceRepository;
import com.apocalipse.zumbi.repository.SurvivorRepository;
import com.apocalipse.zumbi.repository.SurvivorResourceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase(replace = org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE)
@org.springframework.test.context.ActiveProfiles("test")
class TradeControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private SurvivorRepository survivorRepository;
    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private SurvivorResourceRepository survivorResourceRepository;

    @BeforeEach
    void setup() {
        survivorResourceRepository.deleteAll();
        survivorRepository.deleteAll();
        resourceRepository.deleteAll();
        resourceRepository.saveAll(List.of(
                createResource(1L, "Agua", 4),
                createResource(2L, "Comida", 4)
        ));
    }

    private Resource createResource(Long id, String desc, int pontos) {
        Resource r = new Resource();
        r.setId(id);
        r.setDescricao(desc);
        r.setPontos(pontos);
        return r;
    }

    private Survivor createSurvivor(String name) {
        Survivor s = new Survivor();
        s.setNome(name);
        s.setSexo("M");
        s.setIdade(20);
        s.setLatitude(0.0);
        s.setLongitude(0.0);
        return survivorRepository.save(s);
    }

    @Test
    void shouldTradeWithEqualPoints() throws Exception {
        Survivor s1 = createSurvivor("A");
        Survivor s2 = createSurvivor("B");
        SurvivorResource sr1 = new SurvivorResource();
        sr1.setSurvivor(s1);
        sr1.setResource(resourceRepository.findById(1L).orElseThrow());
        sr1.setQuantidade(1);
        sr1.setId(new com.apocalipse.zumbi.domain.SurvivorResourceId(s1.getId(), 1L));
        survivorResourceRepository.save(sr1);

        SurvivorResource sr2 = new SurvivorResource();
        sr2.setSurvivor(s2);
        sr2.setResource(resourceRepository.findById(2L).orElseThrow());
        sr2.setQuantidade(1);
        sr2.setId(new com.apocalipse.zumbi.domain.SurvivorResourceId(s2.getId(), 2L));
        survivorResourceRepository.save(sr2);

        Map<String, Object> payload = Map.of(
                "sobrevivente_origem", s1.getId(),
                "sobrevivente_destino", s2.getId(),
                "itens_origem", List.of(Map.of("idRecurso", 1, "quantidade", 1)),
                "itens_destino", List.of(Map.of("idRecurso", 2, "quantidade", 1))
        );

        mockMvc.perform(post("/inventario/troca")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isOk());
    }
}
