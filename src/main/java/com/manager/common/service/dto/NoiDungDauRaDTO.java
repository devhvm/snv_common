package com.manager.common.service.dto;

import com.manager.common.domain.enumeration.Status;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the NoiDungDauRa entity.
 */
public class NoiDungDauRaDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull
    private Status status;


    private Long noiDungId;

    private Long nhomDanhMucId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getNoiDungId() {
        return noiDungId;
    }

    public void setNoiDungId(Long noiDungId) {
        this.noiDungId = noiDungId;
    }

    public Long getNhomDanhMucId() {
        return nhomDanhMucId;
    }

    public void setNhomDanhMucId(Long nhomDanhMucId) {
        this.nhomDanhMucId = nhomDanhMucId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NoiDungDauRaDTO noiDungDauRaDTO = (NoiDungDauRaDTO) o;
        if (noiDungDauRaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), noiDungDauRaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NoiDungDauRaDTO{" +
            "id=" + getId() +
            ", status='" + getStatus() + "'" +
            ", noiDung=" + getNoiDungId() +
            ", nhomDanhMuc=" + getNhomDanhMucId() +
            "}";
    }
}
