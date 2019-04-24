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
 * A CoQuanChuTri.
 */
@Entity
@Table(name = "co_quan_chu_tri")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CoQuanChuTri extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @OneToMany(mappedBy = "coQuanChuTri")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<TieuChi> tieuChis = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("coQuanChuTris")
    private MaDinhDanhDonVi maDinhDanhDonVi;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public CoQuanChuTri name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public CoQuanChuTri status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<TieuChi> getTieuChis() {
        return tieuChis;
    }

    public CoQuanChuTri tieuChis(Set<TieuChi> tieuChis) {
        this.tieuChis = tieuChis;
        return this;
    }

    public CoQuanChuTri addTieuChi(TieuChi tieuChi) {
        this.tieuChis.add(tieuChi);
        tieuChi.setCoQuanChuTri(this);
        return this;
    }

    public CoQuanChuTri removeTieuChi(TieuChi tieuChi) {
        this.tieuChis.remove(tieuChi);
        tieuChi.setCoQuanChuTri(null);
        return this;
    }

    public void setTieuChis(Set<TieuChi> tieuChis) {
        this.tieuChis = tieuChis;
    }

    public MaDinhDanhDonVi getMaDinhDanhDonVi() {
        return maDinhDanhDonVi;
    }

    public CoQuanChuTri maDinhDanhDonVi(MaDinhDanhDonVi maDinhDanhDonVi) {
        this.maDinhDanhDonVi = maDinhDanhDonVi;
        return this;
    }

    public void setMaDinhDanhDonVi(MaDinhDanhDonVi maDinhDanhDonVi) {
        this.maDinhDanhDonVi = maDinhDanhDonVi;
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
        CoQuanChuTri coQuanChuTri = (CoQuanChuTri) o;
        if (coQuanChuTri.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), coQuanChuTri.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CoQuanChuTri{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
