package com.apocalipse.zumbi.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbsobreviventes")
@lombok.Getter
@lombok.Setter
@lombok.EqualsAndHashCode(of = "id")
@lombok.NoArgsConstructor
public class Survivor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    @Size(max = 1)
    private String sexo;

    @NotNull
    private Integer idade;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    private Boolean zumbi = false;

    @OneToMany(mappedBy = "survivor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SurvivorResource> recursos = new ArrayList<>();
}
