package com.manager.common.service.dto.coquanchutri;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the KyCongBo entity.
 */
public class KyCongBoDetailDTO implements Serializable {

    @NotNull
    private String kyCongBoCode;

    @NotNull
    private String name;

    public String getKyCongBoCode() {
        return kyCongBoCode;
    }

    public void setKyCongBoCode(String kyCongBoCode) {
        this.kyCongBoCode = kyCongBoCode;
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

        KyCongBoDetailDTO kyCongBoDTO = (KyCongBoDetailDTO) o;
        if (kyCongBoDTO.getKyCongBoCode() == null || getKyCongBoCode() == null) {
            return false;
        }
        return Objects.equals(getKyCongBoCode(), kyCongBoDTO.getKyCongBoCode());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getKyCongBoCode());
    }

    @Override
    public String toString() {
        return "KyCongBoDTO{" +
            ", kyCongBoCode='" + getKyCongBoCode() + "'" +
            ", name='" + getName() + "'" +
            "}";
    }
}
