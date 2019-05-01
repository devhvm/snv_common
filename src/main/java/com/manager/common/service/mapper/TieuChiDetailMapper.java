package com.manager.common.service.mapper;

import com.manager.common.domain.TieuChi;
import com.manager.common.service.dto.TieuChiDTO;
import com.manager.common.service.dto.TieuChiDetailDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity TieuChi and its DTO TieuChiDetailDTO.
 */
@Mapper(componentModel = "spring", uses = {KyCongBoMapper.class, CoQuanChuTriMapper.class, ChiTieuMapper.class, NoiDungDetailMapper.class})
public interface TieuChiDetailMapper extends EntityMapper<TieuChiDetailDTO, TieuChi> {

    @Mapping(source = "kyCongBo.id", target = "kyCongBoId")
    @Mapping(source = "coQuanChuTri.id", target = "coQuanChuTriId")
    @Mapping(target = "noiDungs")
    TieuChiDetailDTO toDto(TieuChi tieuChi);

    @Mapping(target = "noiDungs", ignore = true)
    @Mapping(source = "kyCongBoId", target = "kyCongBo")
    @Mapping(source = "coQuanChuTriId", target = "coQuanChuTri")
    @Mapping(source = "chiTieuId", target = "chiTieu")
    TieuChi toEntity(TieuChiDetailDTO tieuChi);

    default TieuChi fromId(Long id) {
        if (id == null) {
            return null;
        }
        TieuChi tieuChi = new TieuChi();
        tieuChi.setId(id);
        return tieuChi;
    }
}
