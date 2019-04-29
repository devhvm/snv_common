package com.manager.common.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.manager.common.domain.enumeration.Status;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * A TieuChiBaoCao.
 */
@Entity
@Table(name = "tieu_chi_bao_cao")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TieuChiBaoCao extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @ManyToOne
    @JsonIgnoreProperties("tieuChiBaoCaos")
    private NhomDanhMuc nhomDanhMuc;

    @ManyToOne
    @JsonIgnoreProperties("tieuChiBaoCaos")
    private TieuChi tieuChi;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public TieuChiBaoCao status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public NhomDanhMuc getNhomDanhMuc() {
        return nhomDanhMuc;
    }

    public TieuChiBaoCao nhomDanhMuc(NhomDanhMuc nhomDanhMuc) {
        this.nhomDanhMuc = nhomDanhMuc;
        return this;
    }

    public void setNhomDanhMuc(NhomDanhMuc nhomDanhMuc) {
        this.nhomDanhMuc = nhomDanhMuc;
    }

    public TieuChi getTieuChi() {
        return tieuChi;
    }

    public TieuChiBaoCao tieuChi(TieuChi tieuChi) {
        this.tieuChi = tieuChi;
        return this;
    }

    public void setTieuChi(TieuChi tieuChi) {
        this.tieuChi = tieuChi;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TieuChiBaoCao tieuChiBaoCao = (TieuChiBaoCao) o;
        if (tieuChiBaoCao.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tieuChiBaoCao.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TieuChiBaoCao{" +
            "id=" + getId() +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
