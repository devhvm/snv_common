package com.manager.common.service.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the NoiDung entity.
 */
public class NoiDungDetailDTO implements Serializable {

    private Long id;

    @NotNull
    private String noiDungCode;

    @NotNull
    private String name;


    private List<NoiDungDauVaoDetailDTO> noiDungDauVaos = new ArrayList();

    private List<NoiDungDauRaDetailDTO> noiDungDauRas = new ArrayList();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNoiDungCode() {
        return noiDungCode;
    }

    public void setNoiDungCode(String noiDungCode) {
        this.noiDungCode = noiDungCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<NoiDungDauVaoDetailDTO> getNoiDungDauVaos() {
        return noiDungDauVaos;
    }

    public void setNoiDungDauVaos(List<NoiDungDauVaoDetailDTO> noiDungDauVaos) {
        this.noiDungDauVaos = noiDungDauVaos;
    }

    public List<NoiDungDauRaDetailDTO> getNoiDungDauRas() {
        return noiDungDauRas;
    }

    public void setNoiDungDauRas(List<NoiDungDauRaDetailDTO> noiDungDauRas) {
        this.noiDungDauRas = noiDungDauRas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NoiDungDetailDTO noiDungDTO = (NoiDungDetailDTO) o;
        if (noiDungDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), noiDungDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NoiDungDTO{" +
            "id=" + getId() +
            ", noiDungCode='" + getNoiDungCode() + "'" +
            ", name='" + getName() + "'" +
            "}";
    }
}
