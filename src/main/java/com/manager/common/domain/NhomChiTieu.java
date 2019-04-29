package com.manager.common.domain;


import com.manager.common.domain.enumeration.Status;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A NhomChiTieu.
 */
@Entity
@Table(name = "nhom_chi_tieu")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class NhomChiTieu extends AbstractAuditingEntity implements Serializable {

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
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @OneToMany(mappedBy = "nhomChiTieu")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ChiTieu> chiTieus = new HashSet<>();
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

    public Set<ChiTieu> getChiTieus() {
        return chiTieus;
    }

    public NhomChiTieu chiTieus(Set<ChiTieu> chiTieus) {
        this.chiTieus = chiTieus;
        return this;
    }

    public NhomChiTieu addChiTieu(ChiTieu chiTieu) {
        this.chiTieus.add(chiTieu);
        chiTieu.setNhomChiTieu(this);
        return this;
    }

    public NhomChiTieu removeChiTieu(ChiTieu chiTieu) {
        this.chiTieus.remove(chiTieu);
        chiTieu.setNhomChiTieu(null);
        return this;
    }

    public void setChiTieus(Set<ChiTieu> chiTieus) {
        this.chiTieus = chiTieus;
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
            ", status='" + getStatus() + "'" +
            "}";
    }
}
