package com.manager.common.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.manager.common.domain.enumeration.Status;

/**
 * A DTO for the CoQuanChuTri entity.
 */
public class CoQuanChuTriDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Status status;


    private Long maDinhDanhDonViId;

    private String maDinhDanhDonViName;

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getMaDinhDanhDonViId() {
        return maDinhDanhDonViId;
    }

    public void setMaDinhDanhDonViId(Long maDinhDanhDonViId) {
        this.maDinhDanhDonViId = maDinhDanhDonViId;
    }

    public String getMaDinhDanhDonViName() {
        return maDinhDanhDonViName;
    }

    public void setMaDinhDanhDonViName(String maDinhDanhDonViName) {
        this.maDinhDanhDonViName = maDinhDanhDonViName;
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
            ", status='" + getStatus() + "'" +
            ", maDinhDanhDonVi=" + getMaDinhDanhDonViId() +
            ", maDinhDanhDonVi='" + getMaDinhDanhDonViName() + "'" +
            "}";
    }
}
