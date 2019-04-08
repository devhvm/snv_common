package com.manager.common.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.manager.common.domain.enumeration.Status;

/**
 * A DTO for the DanhMuc entity.
 */
public class DanhMucDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull
    private String danhMucCode;

    @NotNull
    private String name;

    @NotNull
    private Status status;


    private Long nhomdanhmucId;

    private String nhomdanhmucNhomDanhMucCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDanhMucCode() {
        return danhMucCode;
    }

    public void setDanhMucCode(String danhMucCode) {
        this.danhMucCode = danhMucCode;
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

    public Long getNhomdanhmucId() {
        return nhomdanhmucId;
    }

    public void setNhomdanhmucId(Long nhomDanhMucId) {
        this.nhomdanhmucId = nhomDanhMucId;
    }

    public String getNhomdanhmucNhomDanhMucCode() {
        return nhomdanhmucNhomDanhMucCode;
    }

    public void setNhomdanhmucNhomDanhMucCode(String nhomDanhMucNhomDanhMucCode) {
        this.nhomdanhmucNhomDanhMucCode = nhomDanhMucNhomDanhMucCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DanhMucDTO danhMucDTO = (DanhMucDTO) o;
        if (danhMucDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), danhMucDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DanhMucDTO{" +
            "id=" + getId() +
            ", danhMucCode='" + getDanhMucCode() + "'" +
            ", name='" + getName() + "'" +
            ", status='" + getStatus() + "'" +
            ", nhomdanhmuc=" + getNhomdanhmucId() +
            ", nhomdanhmuc='" + getNhomdanhmucNhomDanhMucCode() + "'" +
            "}";
    }
}
