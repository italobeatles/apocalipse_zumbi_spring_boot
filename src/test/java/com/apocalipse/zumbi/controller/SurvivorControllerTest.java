package com.apocalipse.zumbi.controller;

import com.apocalipse.zumbi.domain.Survivor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase(replace = org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE)
@org.springframework.test.context.ActiveProfiles("test")
class SurvivorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateAndListSurvivor() throws Exception {
        Survivor survivor = new Survivor();
        survivor.setNome("Alice");
        survivor.setSexo("F");
        survivor.setIdade(30);
        survivor.setLatitude(1.0);
        survivor.setLongitude(1.0);

        ResultActions create = mockMvc.perform(post("/sobreviventes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(survivor)))
            .andExpect(status().isCreated());

        mockMvc.perform(get("/sobreviventes"))
            .andExpect(status().isOk());
    }
}
