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
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @ManyToOne
    @JsonIgnoreProperties("noidungs")
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
            ", status='" + getStatus() + "'" +
            "}";
    }
}
