package com.manager.common.service.dto;

import com.manager.common.domain.enumeration.Status;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the NoiDung entity.
 */
public class NoiDungDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull
    private String noiDungCode;

    @NotNull
    private Status status;


    private Long noiDungId;

    private String noiDungChiTieuCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNoiDungCode() {
        return noiDungCode;
    }

    public void setNoiDungCode(String noiDungCode) {
        this.noiDungCode = noiDungCode;
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

    public void setNoiDungId(Long chiTieuId) {
        this.noiDungId = chiTieuId;
    }

    public String getNoiDungChiTieuCode() {
        return noiDungChiTieuCode;
    }

    public void setNoiDungChiTieuCode(String chiTieuChiTieuCode) {
        this.noiDungChiTieuCode = chiTieuChiTieuCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NoiDungDTO noiDungDTO = (NoiDungDTO) o;
        if (noiDungDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), noiDungDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NoiDungDTO{" +
            "id=" + getId() +
            ", noiDungCode='" + getNoiDungCode() + "'" +
            ", status='" + getStatus() + "'" +
            ", noiDung=" + getNoiDungId() +
            ", noiDung='" + getNoiDungChiTieuCode() + "'" +
            "}";
    }
}
