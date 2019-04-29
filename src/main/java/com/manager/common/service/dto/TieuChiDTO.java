package com.manager.common.service.dto;

import com.manager.common.domain.enumeration.Status;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the TieuChi entity.
 */
public class TieuChiDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull
    private Status status;


    private Long kyCongBoId;

    private Long coQuanChuTriId;

    private Long chiTieuId;

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

    public Long getKyCongBoId() {
        return kyCongBoId;
    }

    public void setKyCongBoId(Long kyCongBoId) {
        this.kyCongBoId = kyCongBoId;
    }

    public Long getCoQuanChuTriId() {
        return coQuanChuTriId;
    }

    public void setCoQuanChuTriId(Long coQuanChuTriId) {
        this.coQuanChuTriId = coQuanChuTriId;
    }

    public Long getChiTieuId() {
        return chiTieuId;
    }

    public void setChiTieuId(Long chiTieuId) {
        this.chiTieuId = chiTieuId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TieuChiDTO tieuChiDTO = (TieuChiDTO) o;
        if (tieuChiDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tieuChiDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TieuChiDTO{" +
            "id=" + getId() +
            ", status='" + getStatus() + "'" +
            ", kyCongBo=" + getKyCongBoId() +
            ", coQuanChuTri=" + getCoQuanChuTriId() +
            ", chiTieu=" + getChiTieuId() +
            "}";
    }
}
