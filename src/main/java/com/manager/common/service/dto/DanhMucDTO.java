package com.manager.common.service.dto;

import com.manager.common.domain.enumeration.Status;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the DanhMuc entity.
 */
public class DanhMucDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull
    private String danhMucCode;

    @NotNull
    private String name;

    @NotNull
    private Status status;


    private Long nhomDanhMucId;

    private String nhomDanhMucName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDanhMucCode() {
        return danhMucCode;
    }

    public void setDanhMucCode(String danhMucCode) {
        this.danhMucCode = danhMucCode;
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

    public Long getNhomDanhMucId() {
        return nhomDanhMucId;
    }

    public void setNhomDanhMucId(Long nhomDanhMucId) {
        this.nhomDanhMucId = nhomDanhMucId;
    }

    public String getNhomDanhMucName() {
        return nhomDanhMucName;
    }

    public void setNhomDanhMucName(String nhomDanhMucName) {
        this.nhomDanhMucName = nhomDanhMucName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DanhMucDTO danhMucDTO = (DanhMucDTO) o;
        if (danhMucDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), danhMucDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DanhMucDTO{" +
            "id=" + getId() +
            ", danhMucCode='" + getDanhMucCode() + "'" +
            ", name='" + getName() + "'" +
            ", status='" + getStatus() + "'" +
            ", nhomDanhMuc=" + getNhomDanhMucId() +
            ", nhomDanhMuc='" + getNhomDanhMucName() + "'" +
            "}";
    }
}
