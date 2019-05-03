package com.manager.common.service.dto.coquanchutri;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the TieuChi entity.
 */
public class TieuChiDetailDTO implements Serializable {

    private Long id;

    private KyCongBoDetailDTO kyCongBo;

    private CoQuanChuTriDetailDTO coQuanChuTri;

    private ChiTieuDetailDTO chiTieu;

    private List<NoiDungDetailDTO> noiDungs = new ArrayList();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public KyCongBoDetailDTO getKyCongBo() {
        return kyCongBo;
    }

    public void setKyCongBo(KyCongBoDetailDTO kyCongBo) {
        this.kyCongBo = kyCongBo;
    }

    public ChiTieuDetailDTO getChiTieu() {
        return chiTieu;
    }

    public void setChiTieu(ChiTieuDetailDTO chiTieu) {
        this.chiTieu = chiTieu;
    }

    public CoQuanChuTriDetailDTO getCoQuanChuTri() {
        return coQuanChuTri;
    }

    public void setCoQuanChuTri(CoQuanChuTriDetailDTO coQuanChuTri) {
        this.coQuanChuTri = coQuanChuTri;
    }

    public List<NoiDungDetailDTO> getNoiDungs() {
        return noiDungs;
    }

    public void setNoiDungs(List<NoiDungDetailDTO> noiDungs) {
        this.noiDungs = noiDungs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TieuChiDetailDTO tieuChiDTO = (TieuChiDetailDTO) o;
        if (tieuChiDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tieuChiDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TieuChiDetailDTO{" +
            "id=" + id +
            ", kyCongBo=" + kyCongBo +
            ", coQuanChuTri=" + coQuanChuTri +
            ", chiTieu=" + chiTieu +
            ", noiDungs=" + noiDungs +
            '}';
    }
}
