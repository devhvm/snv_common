package com.manager.common.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

import com.manager.common.domain.enumeration.Status;

/**
 * A NoiDung.
 */
@Entity
@Table(name = "noi_dung")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class NoiDung implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "noi_dung_code", nullable = false)
    private String noiDungCode;

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

    @ManyToOne
    @JsonIgnoreProperties("noiDungs")
    private NhomNoiDung nhomnoidung;

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

    public String getUserName() {
        return userName;
    }

    public NoiDung userName(String userName) {
        this.userName = userName;
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ZonedDateTime getCreateTime() {
        return createTime;
    }

    public NoiDung createTime(ZonedDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public void setCreateTime(ZonedDateTime createTime) {
        this.createTime = createTime;
    }

    public ZonedDateTime getUpdateTime() {
        return updateTime;
    }

    public NoiDung updateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public void setUpdateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
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

    public String getProgram() {
        return program;
    }

    public NoiDung program(String program) {
        this.program = program;
        return this;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public NhomNoiDung getNhomnoidung() {
        return nhomnoidung;
    }

    public NoiDung nhomnoidung(NhomNoiDung nhomNoiDung) {
        this.nhomnoidung = nhomNoiDung;
        return this;
    }

    public void setNhomnoidung(NhomNoiDung nhomNoiDung) {
        this.nhomnoidung = nhomNoiDung;
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
            ", userName='" + getUserName() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", status='" + getStatus() + "'" +
            ", program='" + getProgram() + "'" +
            "}";
    }
}
