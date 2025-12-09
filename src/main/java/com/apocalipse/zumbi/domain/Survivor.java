package com.apocalipse.zumbi.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbsobreviventes")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Boolean getZumbi() {
        return zumbi;
    }

    public void setZumbi(Boolean zumbi) {
        this.zumbi = zumbi;
    }

    public List<SurvivorResource> getRecursos() {
        return recursos;
    }

    public void setRecursos(List<SurvivorResource> recursos) {
        this.recursos = recursos;
    }
}
