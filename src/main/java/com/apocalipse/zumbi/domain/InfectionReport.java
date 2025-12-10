package com.apocalipse.zumbi.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "tbavisos_zumbificacao")
@lombok.Data
public class InfectionReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_sobrevivente")
    private Survivor survivor;

    @ManyToOne
    @JoinColumn(name = "id_informante")
    private Survivor informant;
}
