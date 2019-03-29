package com.manager.common.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

import com.manager.common.domain.enumeration.Status;

/**
 * A PhamVi.
 */
@Entity
@Table(name = "pham_vi")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PhamVi implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "jhi_begin", nullable = false)
    private String begin;

    @NotNull
    @Column(name = "jhi_end", nullable = false)
    private String end;

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

    @OneToOne(mappedBy = "phamvi")
    @JsonIgnore
    private DonVi donvi;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBegin() {
        return begin;
    }

    public PhamVi begin(String begin) {
        this.begin = begin;
        return this;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public PhamVi end(String end) {
        this.end = end;
        return this;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getUserName() {
        return userName;
    }

    public PhamVi userName(String userName) {
        this.userName = userName;
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ZonedDateTime getCreateTime() {
        return createTime;
    }

    public PhamVi createTime(ZonedDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(ZonedDateTime createTime) {
        this.createTime = createTime;
    }

    public ZonedDateTime getUpdateTime() {
        return updateTime;
    }

    public PhamVi updateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Status getStatus() {
        return status;
    }

    public PhamVi status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getProgram() {
        return program;
    }

    public PhamVi program(String program) {
        this.program = program;
        return this;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public DonVi getDonvi() {
        return donvi;
    }

    public PhamVi donvi(DonVi donVi) {
        this.donvi = donVi;
        return this;
    }

    public void setDonvi(DonVi donVi) {
        this.donvi = donVi;
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
        PhamVi phamVi = (PhamVi) o;
        if (phamVi.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), phamVi.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PhamVi{" +
            "id=" + getId() +
            ", begin='" + getBegin() + "'" +
            ", end='" + getEnd() + "'" +
            ", userName='" + getUserName() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", status='" + getStatus() + "'" +
            ", program='" + getProgram() + "'" +
            "}";
    }
}
