package com.manager.common.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
 * A KyCongBo.
 */
@Entity
@Table(name = "ky_cong_bo")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class KyCongBo extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "ky_cong_bo_code", nullable = false)
    private String kyCongBoCode;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @OneToMany(mappedBy = "kyCongBo")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<TieuChi> tieuChis = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKyCongBoCode() {
        return kyCongBoCode;
    }

    public KyCongBo kyCongBoCode(String kyCongBoCode) {
        this.kyCongBoCode = kyCongBoCode;
        return this;
    }

    public void setKyCongBoCode(String kyCongBoCode) {
        this.kyCongBoCode = kyCongBoCode;
    }

    public String getName() {
        return name;
    }

    public KyCongBo name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public KyCongBo status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<TieuChi> getTieuChis() {
        return tieuChis;
    }

    public KyCongBo tieuChis(Set<TieuChi> tieuChis) {
        this.tieuChis = tieuChis;
        return this;
    }

    public KyCongBo addTieuChi(TieuChi tieuChi) {
        this.tieuChis.add(tieuChi);
        tieuChi.setKyCongBo(this);
        return this;
    }

    public KyCongBo removeTieuChi(TieuChi tieuChi) {
        this.tieuChis.remove(tieuChi);
        tieuChi.setKyCongBo(null);
        return this;
    }

    public void setTieuChis(Set<TieuChi> tieuChis) {
        this.tieuChis = tieuChis;
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
        KyCongBo kyCongBo = (KyCongBo) o;
        if (kyCongBo.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), kyCongBo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "KyCongBo{" +
            "id=" + getId() +
            ", kyCongBoCode='" + getKyCongBoCode() + "'" +
            ", name='" + getName() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
