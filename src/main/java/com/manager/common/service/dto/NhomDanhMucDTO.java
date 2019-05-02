package com.manager.common.service.dto;

import com.manager.common.domain.enumeration.Status;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the NhomDanhMuc entity.
 */
public class NhomDanhMucDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull
    private String nhomDanhMucCode;

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

    public String getNhomDanhMucCode() {
        return nhomDanhMucCode;
    }

    public void setNhomDanhMucCode(String nhomDanhMucCode) {
        this.nhomDanhMucCode = nhomDanhMucCode;
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

        NhomDanhMucDTO nhomDanhMucDTO = (NhomDanhMucDTO) o;
        if (nhomDanhMucDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nhomDanhMucDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NhomDanhMucDTO{" +
            "id=" + getId() +
            ", nhomDanhMucCode='" + getNhomDanhMucCode() + "'" +
            ", name='" + getName() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
