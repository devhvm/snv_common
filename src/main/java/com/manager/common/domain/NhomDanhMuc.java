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
 * A NhomDanhMuc.
 */
@Entity
@Table(name = "nhom_danh_muc")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class NhomDanhMuc extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nhom_danh_muc_code", nullable = false)
    private String nhomDanhMucCode;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @OneToMany(mappedBy = "nhomDanhMuc")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<DanhMuc> danhMucs = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("nhomDanhMucs")
    private TieuChiBaoCao tieuChiBaoCao;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNhomDanhMucCode() {
        return nhomDanhMucCode;
    }

    public NhomDanhMuc nhomDanhMucCode(String nhomDanhMucCode) {
        this.nhomDanhMucCode = nhomDanhMucCode;
        return this;
    }

    public void setNhomDanhMucCode(String nhomDanhMucCode) {
        this.nhomDanhMucCode = nhomDanhMucCode;
    }

    public String getName() {
        return name;
    }

    public NhomDanhMuc name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public NhomDanhMuc status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<DanhMuc> getDanhMucs() {
        return danhMucs;
    }

    public NhomDanhMuc danhMucs(Set<DanhMuc> danhMucs) {
        this.danhMucs = danhMucs;
        return this;
    }

    public NhomDanhMuc addDanhMuc(DanhMuc danhMuc) {
        this.danhMucs.add(danhMuc);
        danhMuc.setNhomDanhMuc(this);
        return this;
    }

    public NhomDanhMuc removeDanhMuc(DanhMuc danhMuc) {
        this.danhMucs.remove(danhMuc);
        danhMuc.setNhomDanhMuc(null);
        return this;
    }

    public void setDanhMucs(Set<DanhMuc> danhMucs) {
        this.danhMucs = danhMucs;
    }

    public TieuChiBaoCao getTieuChiBaoCao() {
        return tieuChiBaoCao;
    }

    public NhomDanhMuc tieuChiBaoCao(TieuChiBaoCao tieuChiBaoCao) {
        this.tieuChiBaoCao = tieuChiBaoCao;
        return this;
    }

    public void setTieuChiBaoCao(TieuChiBaoCao tieuChiBaoCao) {
        this.tieuChiBaoCao = tieuChiBaoCao;
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
        NhomDanhMuc nhomDanhMuc = (NhomDanhMuc) o;
        if (nhomDanhMuc.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nhomDanhMuc.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NhomDanhMuc{" +
            "id=" + getId() +
            ", nhomDanhMucCode='" + getNhomDanhMucCode() + "'" +
            ", name='" + getName() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
