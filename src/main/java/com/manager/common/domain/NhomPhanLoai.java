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
 * A NhomPhanLoai.
 */
@Entity
@Table(name = "nhom_phan_loai")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class NhomPhanLoai extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nhom_phan_loai_code", nullable = false)
    private String nhomPhanLoaiCode;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @OneToMany(mappedBy = "nhomphanloai")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<DoiTuong> doituongs = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("nhomphanloais")
    private DonViTinh donvitinh;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNhomPhanLoaiCode() {
        return nhomPhanLoaiCode;
    }

    public NhomPhanLoai nhomPhanLoaiCode(String nhomPhanLoaiCode) {
        this.nhomPhanLoaiCode = nhomPhanLoaiCode;
        return this;
    }

    public void setNhomPhanLoaiCode(String nhomPhanLoaiCode) {
        this.nhomPhanLoaiCode = nhomPhanLoaiCode;
    }

    public String getName() {
        return name;
    }

    public NhomPhanLoai name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public NhomPhanLoai status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<DoiTuong> getDoituongs() {
        return doituongs;
    }

    public NhomPhanLoai doituongs(Set<DoiTuong> doiTuongs) {
        this.doituongs = doiTuongs;
        return this;
    }

    public NhomPhanLoai addDoituong(DoiTuong doiTuong) {
        this.doituongs.add(doiTuong);
        doiTuong.setNhomphanloai(this);
        return this;
    }

    public NhomPhanLoai removeDoituong(DoiTuong doiTuong) {
        this.doituongs.remove(doiTuong);
        doiTuong.setNhomphanloai(null);
        return this;
    }

    public void setDoituongs(Set<DoiTuong> doiTuongs) {
        this.doituongs = doiTuongs;
    }

    public DonViTinh getDonvitinh() {
        return donvitinh;
    }

    public NhomPhanLoai donvitinh(DonViTinh donViTinh) {
        this.donvitinh = donViTinh;
        return this;
    }

    public void setDonvitinh(DonViTinh donViTinh) {
        this.donvitinh = donViTinh;
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
        NhomPhanLoai nhomPhanLoai = (NhomPhanLoai) o;
        if (nhomPhanLoai.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nhomPhanLoai.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NhomPhanLoai{" +
            "id=" + getId() +
            ", nhomPhanLoaiCode='" + getNhomPhanLoaiCode() + "'" +
            ", name='" + getName() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
