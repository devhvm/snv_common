package com.manager.common.domain;


import com.manager.common.domain.enumeration.Status;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * A CoQuanChuTri.
 */
@Entity
@Table(name = "co_quan_chu_tri")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CoQuanChuTri extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "ma_dinh_danh_code", nullable = false)
    private String maDinhDanhCode;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public CoQuanChuTri name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaDinhDanhCode() {
        return maDinhDanhCode;
    }

    public CoQuanChuTri maDinhDanhCode(String maDinhDanhCode) {
        this.maDinhDanhCode = maDinhDanhCode;
        return this;
    }

    public void setMaDinhDanhCode(String maDinhDanhCode) {
        this.maDinhDanhCode = maDinhDanhCode;
    }

    public Status getStatus() {
        return status;
    }

    public CoQuanChuTri status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
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
        CoQuanChuTri coQuanChuTri = (CoQuanChuTri) o;
        if (coQuanChuTri.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), coQuanChuTri.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CoQuanChuTri{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", maDinhDanhCode='" + getMaDinhDanhCode() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
