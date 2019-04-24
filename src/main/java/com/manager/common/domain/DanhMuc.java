package com.manager.common.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

import com.manager.common.domain.enumeration.Status;

/**
 * A DanhMuc.
 */
@Entity
@Table(name = "danh_muc")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DanhMuc extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "danh_muc_code", nullable = false)
    private String danhMucCode;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @ManyToOne
    @JsonIgnoreProperties("danhMucs")
    private NhomDanhMuc nhomDanhMuc;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDanhMucCode() {
        return danhMucCode;
    }

    public DanhMuc danhMucCode(String danhMucCode) {
        this.danhMucCode = danhMucCode;
        return this;
    }

    public void setDanhMucCode(String danhMucCode) {
        this.danhMucCode = danhMucCode;
    }

    public String getName() {
        return name;
    }

    public DanhMuc name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public DanhMuc status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public NhomDanhMuc getNhomDanhMuc() {
        return nhomDanhMuc;
    }

    public DanhMuc nhomDanhMuc(NhomDanhMuc nhomDanhMuc) {
        this.nhomDanhMuc = nhomDanhMuc;
        return this;
    }

    public void setNhomDanhMuc(NhomDanhMuc nhomDanhMuc) {
        this.nhomDanhMuc = nhomDanhMuc;
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
        DanhMuc danhMuc = (DanhMuc) o;
        if (danhMuc.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), danhMuc.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DanhMuc{" +
            "id=" + getId() +
            ", danhMucCode='" + getDanhMucCode() + "'" +
            ", name='" + getName() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
