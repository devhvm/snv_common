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
 * A NoiDung.
 */
@Entity
@Table(name = "noi_dung")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class NoiDung extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "noi_dung_code", nullable = false)
    private String noiDungCode;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @ManyToOne
    @JsonIgnoreProperties("noiDungs")
    private TieuChi tieuChi;

    @OneToMany(mappedBy = "noiDung")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<NoiDungDauVao> noiDungDauVaos = new HashSet<>();
    @OneToMany(mappedBy = "noiDung")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<NoiDungDauRa> noiDungDauRas = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNoiDungCode() {
        return noiDungCode;
    }

    public NoiDung noiDungCode(String noiDungCode) {
        this.noiDungCode = noiDungCode;
        return this;
    }

    public void setNoiDungCode(String noiDungCode) {
        this.noiDungCode = noiDungCode;
    }

    public String getName() {
        return name;
    }

    public NoiDung name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public NoiDung status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public TieuChi getTieuChi() {
        return tieuChi;
    }

    public NoiDung tieuChi(TieuChi tieuChi) {
        this.tieuChi = tieuChi;
        return this;
    }

    public void setTieuChi(TieuChi tieuChi) {
        this.tieuChi = tieuChi;
    }

    public Set<NoiDungDauVao> getNoiDungDauVaos() {
        return noiDungDauVaos;
    }

    public NoiDung noiDungDauVaos(Set<NoiDungDauVao> noiDungDauVaos) {
        this.noiDungDauVaos = noiDungDauVaos;
        return this;
    }

    public NoiDung addNoiDungDauVao(NoiDungDauVao noiDungDauVao) {
        this.noiDungDauVaos.add(noiDungDauVao);
        noiDungDauVao.setNoiDung(this);
        return this;
    }

    public NoiDung removeNoiDungDauVao(NoiDungDauVao noiDungDauVao) {
        this.noiDungDauVaos.remove(noiDungDauVao);
        noiDungDauVao.setNoiDung(null);
        return this;
    }

    public void setNoiDungDauVaos(Set<NoiDungDauVao> noiDungDauVaos) {
        this.noiDungDauVaos = noiDungDauVaos;
    }

    public Set<NoiDungDauRa> getNoiDungDauRas() {
        return noiDungDauRas;
    }

    public NoiDung noiDungDauRas(Set<NoiDungDauRa> noiDungDauRas) {
        this.noiDungDauRas = noiDungDauRas;
        return this;
    }

    public NoiDung addNoiDungDauRa(NoiDungDauRa noiDungDauRa) {
        this.noiDungDauRas.add(noiDungDauRa);
        noiDungDauRa.setNoiDung(this);
        return this;
    }

    public NoiDung removeNoiDungDauRa(NoiDungDauRa noiDungDauRa) {
        this.noiDungDauRas.remove(noiDungDauRa);
        noiDungDauRa.setNoiDung(null);
        return this;
    }

    public void setNoiDungDauRas(Set<NoiDungDauRa> noiDungDauRas) {
        this.noiDungDauRas = noiDungDauRas;
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
        NoiDung noiDung = (NoiDung) o;
        if (noiDung.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), noiDung.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NoiDung{" +
            "id=" + getId() +
            ", noiDungCode='" + getNoiDungCode() + "'" +
            ", name='" + getName() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
