package com.manager.common.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * A MaDinhDanhDonVi.
 */
@Entity
@Table(name = "ma_dinh_danh_don_vi")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MaDinhDanhDonVi extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "ma_dinh_danh_code", nullable = false)
    private String maDinhDanhCode;

    @Column(name = "parent_code")
    private String parentCode;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "jhi_level", nullable = false)
    private String level;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaDinhDanhCode() {
        return maDinhDanhCode;
    }

    public MaDinhDanhDonVi maDinhDanhCode(String maDinhDanhCode) {
        this.maDinhDanhCode = maDinhDanhCode;
        return this;
    }

    public void setMaDinhDanhCode(String maDinhDanhCode) {
        this.maDinhDanhCode = maDinhDanhCode;
    }

    public String getParentCode() {
        return parentCode;
    }

    public MaDinhDanhDonVi parentCode(String parentCode) {
        this.parentCode = parentCode;
        return this;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getName() {
        return name;
    }

    public MaDinhDanhDonVi name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public MaDinhDanhDonVi level(String level) {
        this.level = level;
        return this;
    }

    public void setLevel(String level) {
        this.level = level;
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
        MaDinhDanhDonVi maDinhDanhDonVi = (MaDinhDanhDonVi) o;
        if (maDinhDanhDonVi.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), maDinhDanhDonVi.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MaDinhDanhDonVi{" +
            "id=" + getId() +
            ", maDinhDanhCode='" + getMaDinhDanhCode() + "'" +
            ", parentCode='" + getParentCode() + "'" +
            ", name='" + getName() + "'" +
            ", level='" + getLevel() + "'" +
            "}";
    }
}