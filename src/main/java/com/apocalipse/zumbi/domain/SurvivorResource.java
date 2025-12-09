package com.apocalipse.zumbi.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "tbsobreviventes_recursos")
public class SurvivorResource {

    @EmbeddedId
    private SurvivorResourceId id = new SurvivorResourceId();

    @ManyToOne
    @MapsId("survivorId")
    @JoinColumn(name = "id_sobrevivente")
    private Survivor survivor;

    @ManyToOne
    @MapsId("resourceId")
    @JoinColumn(name = "id_recurso")
    private Resource resource;

    private Integer quantidade;

    public SurvivorResourceId getId() {
        return id;
    }

    public void setId(SurvivorResourceId id) {
        this.id = id;
    }

    public Survivor getSurvivor() {
        return survivor;
    }

    public void setSurvivor(Survivor survivor) {
        this.survivor = survivor;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
