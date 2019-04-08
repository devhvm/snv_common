package com.manager.common.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.manager.common.domain.enumeration.Status;

/**
 * A DTO for the DoiTuong entity.
 */
public class DoiTuongDTO implements Serializable {

    private Long id;

    @NotNull
    private String doiTuongCode;

    @NotNull
    private String name;

    @NotNull
    private Status status;


    private Long nhomphanloaiId;

    private String nhomphanloaiNhomPhanLoaiCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getNhomphanloaiId() {
        return nhomphanloaiId;
    }

    public void setNhomphanloaiId(Long nhomPhanLoaiId) {
        this.nhomphanloaiId = nhomPhanLoaiId;
    }

    public String getNhomphanloaiNhomPhanLoaiCode() {
        return nhomphanloaiNhomPhanLoaiCode;
    }

    public void setNhomphanloaiNhomPhanLoaiCode(String nhomPhanLoaiNhomPhanLoaiCode) {
        this.nhomphanloaiNhomPhanLoaiCode = nhomPhanLoaiNhomPhanLoaiCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DoiTuongDTO doiTuongDTO = (DoiTuongDTO) o;
        if (doiTuongDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), doiTuongDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DoiTuongDTO{" +
            "id=" + getId() +
            ", doiTuongCode='" + getDoiTuongCode() + "'" +
            ", name='" + getName() + "'" +
            ", status='" + getStatus() + "'" +
            ", nhomphanloai=" + getNhomphanloaiId() +
            ", nhomphanloai='" + getNhomphanloaiNhomPhanLoaiCode() + "'" +
            "}";
    }
}
