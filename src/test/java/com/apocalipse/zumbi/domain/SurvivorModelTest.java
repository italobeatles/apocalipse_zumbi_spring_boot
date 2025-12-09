package com.apocalipse.zumbi.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import com.apocalipse.zumbi.repository.SurvivorRepository;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(SurvivorRepository.class)
class SurvivorModelTest {

    @Autowired
    private SurvivorRepository survivorRepository;

    @Test
    void shouldPersistSurvivor() {
        Survivor survivor = new Survivor();
        survivor.setNome("Bob");
        survivor.setSexo("M");
        survivor.setIdade(20);
        survivor.setLatitude(0.0);
        survivor.setLongitude(0.0);
        Survivor saved = survivorRepository.save(survivor);
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getNome()).isEqualTo("Bob");
    }
}
