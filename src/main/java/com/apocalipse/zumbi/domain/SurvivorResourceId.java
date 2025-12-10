package com.apocalipse.zumbi.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@lombok.Getter
@lombok.Setter
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@lombok.EqualsAndHashCode
public class SurvivorResourceId implements Serializable {
    @Column(name = "id_sobrevivente")
    private Long survivorId;

    @Column(name = "id_recurso")
    private Long resourceId;
}
