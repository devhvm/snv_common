package com.manager.common.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.manager.common.domain.enumeration.Status;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * A NoiDungDauVao.
 */
@Entity
@Table(name = "noi_dung_dau_Vao")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class NoiDungDauVao extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @ManyToOne
    @JsonIgnoreProperties("noiDungDauVaos")
    private NoiDung noiDung;

    @ManyToOne
    @JsonIgnoreProperties("noiDungDauVaos")
    private NhomDanhMuc nhomDanhMuc;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public NoiDungDauVao status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public NoiDung getNoiDung() {
        return noiDung;
    }

    public NoiDungDauVao noiDung(NoiDung noiDung) {
        this.noiDung = noiDung;
        return this;
    }

    public void setNoiDung(NoiDung noiDung) {
        this.noiDung = noiDung;
    }

    public NhomDanhMuc getNhomDanhMuc() {
        return nhomDanhMuc;
    }

    public NoiDungDauVao nhomDanhMuc(NhomDanhMuc nhomDanhMuc) {
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
        NoiDungDauVao noiDungDauVao = (NoiDungDauVao) o;
        if (noiDungDauVao.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), noiDungDauVao.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NoiDungDauVao{" +
            "id=" + getId() +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
