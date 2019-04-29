package com.manager.common.service.dto;

import com.manager.common.domain.enumeration.Status;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the TieuChiBaoCao entity.
 */
public class TieuChiBaoCaoDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull
    private Status status;


    private Long nhomDanhMucId;

    private Long tieuChiId;

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

    public Long getNhomDanhMucId() {
        return nhomDanhMucId;
    }

    public void setNhomDanhMucId(Long nhomDanhMucId) {
        this.nhomDanhMucId = nhomDanhMucId;
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

        TieuChiBaoCaoDTO tieuChiBaoCaoDTO = (TieuChiBaoCaoDTO) o;
        if (tieuChiBaoCaoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tieuChiBaoCaoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TieuChiBaoCaoDTO{" +
            "id=" + getId() +
            ", status='" + getStatus() + "'" +
            ", nhomDanhMuc=" + getNhomDanhMucId() +
            ", tieuChi=" + getTieuChiId() +
            "}";
    }
}
