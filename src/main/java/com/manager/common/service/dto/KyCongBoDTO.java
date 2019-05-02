package com.manager.common.service.dto;

import com.manager.common.domain.enumeration.Status;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the KyCongBo entity.
 */
public class KyCongBoDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull
    private String kyCongBoCode;

    @NotNull
    private String name;

    @NotNull
    private Status status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKyCongBoCode() {
        return kyCongBoCode;
    }

    public void setKyCongBoCode(String kyCongBoCode) {
        this.kyCongBoCode = kyCongBoCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        KyCongBoDTO kyCongBoDTO = (KyCongBoDTO) o;
        if (kyCongBoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), kyCongBoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "KyCongBoDTO{" +
            "id=" + getId() +
            ", kyCongBoCode='" + getKyCongBoCode() + "'" +
            ", name='" + getName() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
