package com.manager.common.service.dto;

import com.manager.common.domain.enumeration.Status;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the NhomChiTieu entity.
 */
public class NhomChiTieuDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull
    private String nhomChiTieuCode;

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

    public String getNhomChiTieuCode() {
        return nhomChiTieuCode;
    }

    public void setNhomChiTieuCode(String nhomChiTieuCode) {
        this.nhomChiTieuCode = nhomChiTieuCode;
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

        NhomChiTieuDTO nhomChiTieuDTO = (NhomChiTieuDTO) o;
        if (nhomChiTieuDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nhomChiTieuDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NhomChiTieuDTO{" +
            "id=" + getId() +
            ", nhomChiTieuCode='" + getNhomChiTieuCode() + "'" +
            ", name='" + getName() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
