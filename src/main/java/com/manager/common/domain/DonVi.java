package com.manager.common.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.manager.common.domain.enumeration.Status;

/**
 * A DonVi.
 */
@Entity
@Table(name = "don_vi")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DonVi extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "don_vi_code", nullable = false)
    private String donViCode;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "user_name", nullable = false)
    private String userName;

    @NotNull
    @Column(name = "create_time", nullable = false)
    private ZonedDateTime createTime;

    @NotNull
    @Column(name = "update_time", nullable = false)
    private ZonedDateTime updateTime;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @NotNull
    @Column(name = "program", nullable = false)
    private String program;

    @OneToOne
    @JoinColumn(unique = true)
    private PhamVi phamvi;

    @OneToMany(mappedBy = "donvi")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<NhomPhanLoai> nhomphanloais = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDonViCode() {
        return donViCode;
    }

    public DonVi donViCode(String donViCode) {
        this.donViCode = donViCode;
        return this;
    }

    public void setDonViCode(String donViCode) {
        this.donViCode = donViCode;
    }

    public String getName() {
        return name;
    }

    public DonVi name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public DonVi userName(String userName) {
        this.userName = userName;
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ZonedDateTime getCreateTime() {
        return createTime;
    }

    public DonVi createTime(ZonedDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(ZonedDateTime createTime) {
        this.createTime = createTime;
    }

    public ZonedDateTime getUpdateTime() {
        return updateTime;
    }

    public DonVi updateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Status getStatus() {
        return status;
    }

    public DonVi status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getProgram() {
        return program;
    }

    public DonVi program(String program) {
        this.program = program;
        return this;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public PhamVi getPhamvi() {
        return phamvi;
    }

    public DonVi phamvi(PhamVi phamVi) {
        this.phamvi = phamVi;
        return this;
    }

    public void setPhamvi(PhamVi phamVi) {
        this.phamvi = phamVi;
    }

    public Set<NhomPhanLoai> getNhomphanloais() {
        return nhomphanloais;
    }

    public DonVi nhomphanloais(Set<NhomPhanLoai> nhomPhanLoais) {
        this.nhomphanloais = nhomPhanLoais;
        return this;
    }

    public DonVi addNhomphanloai(NhomPhanLoai nhomPhanLoai) {
        this.nhomphanloais.add(nhomPhanLoai);
        nhomPhanLoai.setDonvi(this);
        return this;
    }

    public DonVi removeNhomphanloai(NhomPhanLoai nhomPhanLoai) {
        this.nhomphanloais.remove(nhomPhanLoai);
        nhomPhanLoai.setDonvi(null);
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
        DonVi donVi = (DonVi) o;
        if (donVi.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), donVi.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DonVi{" +
            "id=" + getId() +
            ", donViCode='" + getDonViCode() + "'" +
            ", name='" + getName() + "'" +
            ", userName='" + getUserName() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", status='" + getStatus() + "'" +
            ", program='" + getProgram() + "'" +
            "}";
    }
}
