package com.manager.common.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.manager.common.domain.enumeration.Status;

/**
 * A DTO for the NoiDung entity.
 */
public class NoiDungDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull
    private String noiDungCode;

    @NotNull
    private String name;

    @NotNull
    private Status status;


    private Long tieuChiId;

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

    public Long getTieuChiId() {
        return tieuChiId;
    }

    public void setTieuChiId(Long tieuChiId) {
        this.tieuChiId = tieuChiId;
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
            ", name='" + getName() + "'" +
            ", status='" + getStatus() + "'" +
            ", tieuChi=" + getTieuChiId() +
            "}";
    }
}
