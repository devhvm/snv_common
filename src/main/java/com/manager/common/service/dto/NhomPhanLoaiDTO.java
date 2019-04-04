package com.manager.common.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.manager.common.domain.enumeration.Status;

/**
 * A DTO for the NhomPhanLoai entity.
 */
public class NhomPhanLoaiDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull
    private String nhomPhanLoaiCode;

    @NotNull
    private String name;

    @NotNull
    private Status status;


    private Long donvitinhId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNhomPhanLoaiCode() {
        return nhomPhanLoaiCode;
    }

    public void setNhomPhanLoaiCode(String nhomPhanLoaiCode) {
        this.nhomPhanLoaiCode = nhomPhanLoaiCode;
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

    public Long getDonvitinhId() {
        return donvitinhId;
    }

    public void setDonvitinhId(Long donViTinhId) {
        this.donvitinhId = donViTinhId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NhomPhanLoaiDTO nhomPhanLoaiDTO = (NhomPhanLoaiDTO) o;
        if (nhomPhanLoaiDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), nhomPhanLoaiDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NhomPhanLoaiDTO{" +
            "id=" + getId() +
            ", nhomPhanLoaiCode='" + getNhomPhanLoaiCode() + "'" +
            ", name='" + getName() + "'" +
            ", status='" + getStatus() + "'" +
            ", donvitinh=" + getDonvitinhId() +
            "}";
    }
}
