package com.manager.common.service.dto;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import com.manager.common.domain.enumeration.Status;

/**
 * A DTO for the DonVi entity.
 */
public class DonViDTO extends AbstractAuditingDTO implements Serializable {

    private Long id;

    @NotNull
    private String donViCode;

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


    private Long phamviId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDonViCode() {
        return donViCode;
    }

    public void setDonViCode(String donViCode) {
        this.donViCode = donViCode;
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

    public Long getPhamviId() {
        return phamviId;
    }

    public void setPhamviId(Long phamViId) {
        this.phamviId = phamViId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DonViDTO donViDTO = (DonViDTO) o;
        if (donViDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), donViDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DonViDTO{" +
            "id=" + getId() +
            ", donViCode='" + getDonViCode() + "'" +
            ", name='" + getName() + "'" +
            ", userName='" + getUserName() + "'" +
            ", createTime='" + getCreateTime() + "'" +
            ", updateTime='" + getUpdateTime() + "'" +
            ", status='" + getStatus() + "'" +
            ", program='" + getProgram() + "'" +
            ", phamvi=" + getPhamviId() +
            "}";
    }
}
