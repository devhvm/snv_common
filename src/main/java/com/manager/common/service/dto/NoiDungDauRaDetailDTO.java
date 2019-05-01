package com.manager.common.service.dto;

import com.manager.common.domain.enumeration.Status;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the NoiDungDauRa entity.
 */
public class NoiDungDauRaDetailDTO implements Serializable {

    private String nhomDanhMucCode;

    private String nhomDanhMucName;

    private List<DanhMucDetailDTO> danhMucs = new ArrayList();

    public String getNhomDanhMucCode() {
        return nhomDanhMucCode;
    }

    public void setNhomDanhMucCode(String nhomDanhMucCode) {
        this.nhomDanhMucCode = nhomDanhMucCode;
    }

    public String getNhomDanhMucName() {
        return nhomDanhMucName;
    }

    public void setNhomDanhMucName(String nhomDanhMucName) {
        this.nhomDanhMucName = nhomDanhMucName;
    }

    public List<DanhMucDetailDTO> getDanhMucs() {
        return danhMucs;
    }

    public void setDanhMucs(List<DanhMucDetailDTO> danhMucs) {
        this.danhMucs = danhMucs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NoiDungDauRaDetailDTO noiDungDauRaDTO = (NoiDungDauRaDetailDTO) o;
        if (noiDungDauRaDTO.getNhomDanhMucCode() == null || getNhomDanhMucCode() == null) {
            return false;
        }
        return Objects.equals(getNhomDanhMucCode(), noiDungDauRaDTO.getNhomDanhMucCode());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getNhomDanhMucCode());
    }

    @Override
    public String toString() {
        return "NoiDungDauRaDetailDTO{" +
            "nhomDanhMucCode='" + nhomDanhMucCode + '\'' +
            ", nhomDanhMucName='" + nhomDanhMucName + '\'' +
            ", danhMucs=" + danhMucs +
            '}';
    }
}
