package com.apocalipse.zumbi.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "tbavisos_zumbificacao")
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

    public Long getId() {
        return id;
    }

    public Survivor getSurvivor() {
        return survivor;
    }

    public Survivor getInformant() {
        return informant;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSurvivor(Survivor survivor) {
        this.survivor = survivor;
    }

    public void setInformant(Survivor informant) {
        this.informant = informant;
    }
}
