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
 * A DonViTinh.
 */
@Entity
@Table(name = "don_vi_tinh")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DonViTinh implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "don_vi_tinh_code", nullable = false)
    private String donViTinhCode;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @OneToMany(mappedBy = "donvitinh")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<NhomPhanLoai> nhomphanloais = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDonViTinhCode() {
        return donViTinhCode;
    }

    public DonViTinh donViTinhCode(String donViTinhCode) {
        this.donViTinhCode = donViTinhCode;
        return this;
    }

    public void setDonViTinhCode(String donViTinhCode) {
        this.donViTinhCode = donViTinhCode;
    }

    public String getName() {
        return name;
    }

    public DonViTinh name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public DonViTinh status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<NhomPhanLoai> getNhomphanloais() {
        return nhomphanloais;
    }

    public DonViTinh nhomphanloais(Set<NhomPhanLoai> nhomPhanLoais) {
        this.nhomphanloais = nhomPhanLoais;
        return this;
    }

    public DonViTinh addNhomphanloai(NhomPhanLoai nhomPhanLoai) {
        this.nhomphanloais.add(nhomPhanLoai);
        nhomPhanLoai.setDonvitinh(this);
        return this;
    }

    public DonViTinh removeNhomphanloai(NhomPhanLoai nhomPhanLoai) {
        this.nhomphanloais.remove(nhomPhanLoai);
        nhomPhanLoai.setDonvitinh(null);
        return this;
    }

    public void setNhomphanloais(Set<NhomPhanLoai> nhomPhanLoais) {
        this.nhomphanloais = nhomPhanLoais;
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
        DonViTinh donViTinh = (DonViTinh) o;
        if (donViTinh.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), donViTinh.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DonViTinh{" +
            "id=" + getId() +
            ", donViTinhCode='" + getDonViTinhCode() + "'" +
            ", name='" + getName() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
