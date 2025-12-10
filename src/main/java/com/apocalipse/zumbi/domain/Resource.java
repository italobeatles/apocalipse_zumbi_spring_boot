package com.apocalipse.zumbi.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbrecursos")
@lombok.Getter
@lombok.Setter
@lombok.EqualsAndHashCode(of = "id")
@lombok.NoArgsConstructor
public class Resource {
    @Id
    private Long id;
    private String descricao;
    private Integer pontos;
}
