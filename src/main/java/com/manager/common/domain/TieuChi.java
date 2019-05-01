package com.manager.common.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.manager.common.domain.enumeration.Status;

/**
 * A TieuChi.
 */
@Entity
@Table(name = "tieu_chi")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TieuChi extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;


    @Column(name = "co_quan_chu_tri_id", nullable = false, insertable= false, updatable=false)
    private Long coQuanChuTriId;

    @OneToMany(mappedBy = "tieuChi")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<NoiDung> noiDungs = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("tieuChis")
    private KyCongBo kyCongBo;

    @ManyToOne
    @JsonIgnoreProperties("tieuChis")
    private CoQuanChuTri coQuanChuTri;

    @ManyToOne
    @JsonIgnoreProperties("tieuChis")
    private ChiTieu chiTieu;

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

    public TieuChi status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<NoiDung> getNoiDungs() {
        return noiDungs;
    }

    public TieuChi noiDungs(Set<NoiDung> noiDungs) {
        this.noiDungs = noiDungs;
        return this;
    }

    public TieuChi addNoiDung(NoiDung noiDung) {
        this.noiDungs.add(noiDung);
        noiDung.setTieuChi(this);
        return this;
    }

    public TieuChi removeNoiDung(NoiDung noiDung) {
        this.noiDungs.remove(noiDung);
        noiDung.setTieuChi(null);
        return this;
    }

    public void setNoiDungs(Set<NoiDung> noiDungs) {
        this.noiDungs = noiDungs;
    }

    public KyCongBo getKyCongBo() {
        return kyCongBo;
    }

    public TieuChi kyCongBo(KyCongBo kyCongBo) {
        this.kyCongBo = kyCongBo;
        return this;
    }

    public void setKyCongBo(KyCongBo kyCongBo) {
        this.kyCongBo = kyCongBo;
    }

    public CoQuanChuTri getCoQuanChuTri() {
        return coQuanChuTri;
    }

    public TieuChi coQuanChuTri(CoQuanChuTri coQuanChuTri) {
        this.coQuanChuTri = coQuanChuTri;
        return this;
    }

    public void setCoQuanChuTri(CoQuanChuTri coQuanChuTri) {
        this.coQuanChuTri = coQuanChuTri;
    }

    public ChiTieu getChiTieu() {
        return chiTieu;
    }

    public TieuChi chiTieu(ChiTieu chiTieu) {
        this.chiTieu = chiTieu;
        return this;
    }

    public void setChiTieu(ChiTieu chiTieu) {
        this.chiTieu = chiTieu;
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
        TieuChi tieuChi = (TieuChi) o;
        if (tieuChi.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tieuChi.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TieuChi{" +
            "id=" + getId() +
            ", status='" + getStatus() + "'" +
            "}";
    }
}