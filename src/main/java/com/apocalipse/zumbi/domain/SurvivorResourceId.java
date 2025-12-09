package com.apocalipse.zumbi.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SurvivorResourceId implements Serializable {
    @Column(name = "id_sobrevivente")
    private Long survivorId;

    @Column(name = "id_recurso")
    private Long resourceId;

    public SurvivorResourceId() {}

    public SurvivorResourceId(Long survivorId, Long resourceId) {
        this.survivorId = survivorId;
        this.resourceId = resourceId;
    }

    public Long getSurvivorId() {
        return survivorId;
    }

    public void setSurvivorId(Long survivorId) {
        this.survivorId = survivorId;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SurvivorResourceId that = (SurvivorResourceId) o;
        return Objects.equals(survivorId, that.survivorId) && Objects.equals(resourceId, that.resourceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(survivorId, resourceId);
    }
}
