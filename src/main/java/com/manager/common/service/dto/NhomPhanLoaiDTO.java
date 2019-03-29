package com.manager.common.service.dto;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.manager.common.domain.enumeration.Status;

/**
 * A DTO for the NhomPhanLoai entity.
 */
public class NhomPhanLoaiDTO implements Serializable {

    private Long id;

    @NotNull
    private String nhomPhanLoaiCode;

    @NotNull
    private String name;

    @NotNull
    private String userName;

    @NotNull
    private ZonedDateTime createTime;

    @NotNull
    private ZonedDateTime updateTime;

    @NotNull
    private Status status;

    @NotNull
    private String program;


    private Long donviId;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ZonedDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(ZonedDateTime createTime) {
        this.createTime = createTime;
    }

    public ZonedDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(ZonedDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public Long getDonviId() {
        return donviId;
    }

    public void setDonviId(Long donViId) {
        this.donviId = donViId;
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
            ", userName='" + getUserName() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", status='" + getStatus() + "'" +
            ", program='" + getProgram() + "'" +
            ", donvi=" + getDonviId() +
            "}";
    }
}
