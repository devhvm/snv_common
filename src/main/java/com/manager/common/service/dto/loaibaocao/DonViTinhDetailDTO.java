package com.manager.common.service.dto.loaibaocao;

import com.manager.common.domain.enumeration.Status;
import com.manager.common.service.dto.AbstractAuditingDTO;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the DonViTinh entity.
 */
public class DonViTinhDetailDTO implements Serializable {

    private Long id;

    @NotNull
    private String donViTinhCode;

    @NotNull
    private String name;

    @NotNull
    private Status status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDonViTinhCode() {
        return donViTinhCode;
    }

    public void setDonViTinhCode(String donViTinhCode) {
        this.donViTinhCode = donViTinhCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

        DonViTinhDetailDTO donViTinhDTO = (DonViTinhDetailDTO) o;
        if (donViTinhDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), donViTinhDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DonViTinhDTO{" +
            "id=" + getId() +
            ", donViTinhCode='" + getDonViTinhCode() + "'" +
            ", name='" + getName() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
