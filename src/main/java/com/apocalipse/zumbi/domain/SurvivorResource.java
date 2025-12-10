package com.apocalipse.zumbi.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "tbsobreviventes_recursos")
@lombok.Getter
@lombok.Setter
@lombok.NoArgsConstructor
@lombok.EqualsAndHashCode(of = "id")
@lombok.ToString(exclude = {"survivor", "resource"})
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

    public void setSurvivor(Survivor survivor) {
        this.survivor = survivor;
        if (id == null) {
            id = new SurvivorResourceId();
        }
        id.setSurvivorId(survivor != null ? survivor.getId() : null);
    }

    public void setResource(Resource resource) {
        this.resource = resource;
        if (id == null) {
            id = new SurvivorResourceId();
        }
        id.setResourceId(resource != null ? resource.getId() : null);
    }
}
