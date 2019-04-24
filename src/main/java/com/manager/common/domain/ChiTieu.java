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
 * A ChiTieu.
 */
@Entity
@Table(name = "chi_tieu")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ChiTieu extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "chi_tieu_code", nullable = false)
    private String chiTieuCode;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @OneToMany(mappedBy = "chiTieu")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<TieuChi> tieuChis = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("chiTieus")
    private NhomChiTieu nhomChiTieu;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChiTieuCode() {
        return chiTieuCode;
    }

    public ChiTieu chiTieuCode(String chiTieuCode) {
        this.chiTieuCode = chiTieuCode;
        return this;
    }

    public void setChiTieuCode(String chiTieuCode) {
        this.chiTieuCode = chiTieuCode;
    }

    public String getName() {
        return name;
    }

    public ChiTieu name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public ChiTieu status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<TieuChi> getTieuChis() {
        return tieuChis;
    }

    public ChiTieu tieuChis(Set<TieuChi> tieuChis) {
        this.tieuChis = tieuChis;
        return this;
    }

    public ChiTieu addTieuChi(TieuChi tieuChi) {
        this.tieuChis.add(tieuChi);
        tieuChi.setChiTieu(this);
        return this;
    }

    public ChiTieu removeTieuChi(TieuChi tieuChi) {
        this.tieuChis.remove(tieuChi);
        tieuChi.setChiTieu(null);
        return this;
    }

    public void setTieuChis(Set<TieuChi> tieuChis) {
        this.tieuChis = tieuChis;
    }

    public NhomChiTieu getNhomChiTieu() {
        return nhomChiTieu;
    }

    public ChiTieu nhomChiTieu(NhomChiTieu nhomChiTieu) {
        this.nhomChiTieu = nhomChiTieu;
        return this;
    }

    public void setNhomChiTieu(NhomChiTieu nhomChiTieu) {
        this.nhomChiTieu = nhomChiTieu;
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
        ChiTieu chiTieu = (ChiTieu) o;
        if (chiTieu.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), chiTieu.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ChiTieu{" +
            "id=" + getId() +
            ", chiTieuCode='" + getChiTieuCode() + "'" +
            ", name='" + getName() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
