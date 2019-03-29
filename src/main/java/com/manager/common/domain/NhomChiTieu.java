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
 * A NhomChiTieu.
 */
@Entity
@Table(name = "nhom_chi_tieu")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class NhomChiTieu implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nhom_chi_tieu_code", nullable = false)
    private String nhomChiTieuCode;

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

    @OneToMany(mappedBy = "nhomchitieu")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ChiTieu> chitieus = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNhomChiTieuCode() {
        return nhomChiTieuCode;
    }

    public NhomChiTieu nhomChiTieuCode(String nhomChiTieuCode) {
        this.nhomChiTieuCode = nhomChiTieuCode;
        return this;
    }

    public void setNhomChiTieuCode(String nhomChiTieuCode) {
        this.nhomChiTieuCode = nhomChiTieuCode;
    }

    public String getName() {
        return name;
    }

    public NhomChiTieu name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public NhomChiTieu userName(String userName) {
        this.userName = userName;
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ZonedDateTime getCreateTime() {
        return createTime;
    }

    public NhomChiTieu createTime(ZonedDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(ZonedDateTime createTime) {
        this.createTime = createTime;
    }

    public ZonedDateTime getUpdateTime() {
        return updateTime;
    }

    public NhomChiTieu updateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Status getStatus() {
        return status;
    }

    public NhomChiTieu status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getProgram() {
        return program;
    }

    public NhomChiTieu program(String program) {
        this.program = program;
        return this;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public Set<ChiTieu> getChitieus() {
        return chitieus;
    }

    public NhomChiTieu chitieus(Set<ChiTieu> chiTieus) {
        this.chitieus = chiTieus;
        return this;
    }

    public NhomChiTieu addChitieu(ChiTieu chiTieu) {
        this.chitieus.add(chiTieu);
        chiTieu.setNhomchitieu(this);
        return this;
    }

    public NhomChiTieu removeChitieu(ChiTieu chiTieu) {
        this.chitieus.remove(chiTieu);
        chiTieu.setNhomchitieu(null);
        return this;
    }

    public void setChitieus(Set<ChiTieu> chiTieus) {
        this.chitieus = chiTieus;
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
        NhomChiTieu nhomChiTieu = (NhomChiTieu) o;
        if (nhomChiTieu.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nhomChiTieu.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NhomChiTieu{" +
            "id=" + getId() +
            ", nhomChiTieuCode='" + getNhomChiTieuCode() + "'" +
            ", name='" + getName() + "'" +
            ", userName='" + getUserName() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", status='" + getStatus() + "'" +
            ", program='" + getProgram() + "'" +
            "}";
    }
}
