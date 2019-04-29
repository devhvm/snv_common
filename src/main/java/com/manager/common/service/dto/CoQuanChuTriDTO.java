package com.manager.common.service.dto;

import com.manager.common.domain.enumeration.Status;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the CoQuanChuTri entity.
 */
public class CoQuanChuTriDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String maDinhDanhCode;

    @NotNull
    private Status status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaDinhDanhCode() {
        return maDinhDanhCode;
    }

    public void setMaDinhDanhCode(String maDinhDanhCode) {
        this.maDinhDanhCode = maDinhDanhCode;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CoQuanChuTriDTO coQuanChuTriDTO = (CoQuanChuTriDTO) o;
        if (coQuanChuTriDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), coQuanChuTriDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CoQuanChuTriDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", maDinhDanhCode='" + getMaDinhDanhCode() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
