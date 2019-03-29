package com.manager.common.domain;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

import com.manager.common.domain.enumeration.Status;

/**
 * A NhomDanhMuc.
 */
@Entity
@Table(name = "nhom_danh_muc")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class NhomDanhMuc implements Serializable {

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

    public String getUserName() {
        return userName;
    }

    public NhomDanhMuc userName(String userName) {
        this.userName = userName;
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ZonedDateTime getCreateTime() {
        return createTime;
    }

    public NhomDanhMuc createTime(ZonedDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(ZonedDateTime createTime) {
        this.createTime = createTime;
    }

    public ZonedDateTime getUpdateTime() {
        return updateTime;
    }

    public NhomDanhMuc updateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
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

    public String getProgram() {
        return program;
    }

    public NhomDanhMuc program(String program) {
        this.program = program;
        return this;
    }

    public void setProgram(String program) {
        this.program = program;
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
            ", userName='" + getUserName() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", status='" + getStatus() + "'" +
            ", program='" + getProgram() + "'" +
            "}";
    }
}
