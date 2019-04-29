package com.manager.common.service.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the MaDinhDanhDonVi entity.
 */
public class MaDinhDanhDonViDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull
    private String maDinhDanhCode;

    private String parentCode;

    @NotNull
    private String name;

    @NotNull
    private String level;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaDinhDanhCode() {
        return maDinhDanhCode;
    }

    public void setMaDinhDanhCode(String maDinhDanhCode) {
        this.maDinhDanhCode = maDinhDanhCode;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MaDinhDanhDonViDTO maDinhDanhDonViDTO = (MaDinhDanhDonViDTO) o;
        if (maDinhDanhDonViDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), maDinhDanhDonViDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MaDinhDanhDonViDTO{" +
            "id=" + getId() +
            ", maDinhDanhCode='" + getMaDinhDanhCode() + "'" +
            ", parentCode='" + getParentCode() + "'" +
            ", name='" + getName() + "'" +
            ", level='" + getLevel() + "'" +
            "}";
    }
}
