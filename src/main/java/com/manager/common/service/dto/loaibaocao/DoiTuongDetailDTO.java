package com.manager.common.service.dto.loaibaocao;

import com.manager.common.domain.enumeration.Status;
import com.manager.common.service.dto.AbstractAuditingDTO;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the DoiTuong entity.
 */
public class DoiTuongDetailDTO implements Serializable {

    @NotNull
    private String doiTuongCode;

    @NotNull
    private String name;

    public String getDoiTuongCode() {
        return doiTuongCode;
    }

    public void setDoiTuongCode(String doiTuongCode) {
        this.doiTuongCode = doiTuongCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DoiTuongDetailDTO doiTuongDTO = (DoiTuongDetailDTO) o;
        if (doiTuongDTO.getDoiTuongCode() == null || getDoiTuongCode() == null) {
            return false;
        }
        return Objects.equals(getDoiTuongCode(), doiTuongDTO.getDoiTuongCode());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getDoiTuongCode());
    }

    @Override
    public String toString() {
        return "DoiTuongDTO{" +
            ", doiTuongCode='" + getDoiTuongCode() + "'" +
            ", name='" + getName() + "'" +
            "}";
    }
}
