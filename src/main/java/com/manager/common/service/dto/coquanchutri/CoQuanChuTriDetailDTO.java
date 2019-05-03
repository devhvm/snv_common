package com.manager.common.service.dto.coquanchutri;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the CoQuanChuTri entity.
 */
public class CoQuanChuTriDetailDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String maDinhDanhCode;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CoQuanChuTriDetailDTO coQuanChuTriDTO = (CoQuanChuTriDetailDTO) o;
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
            "}";
    }
}
