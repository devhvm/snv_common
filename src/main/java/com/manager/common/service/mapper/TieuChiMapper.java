package com.manager.common.service.mapper;

import com.manager.common.domain.TieuChi;
import com.manager.common.service.dto.TieuChiDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity TieuChi and its DTO TieuChiDTO.
 */
@Mapper(componentModel = "spring", uses = {KyCongBoMapper.class, CoQuanChuTriMapper.class, ChiTieuMapper.class})
public interface TieuChiMapper extends EntityMapper<TieuChiDTO, TieuChi> {

    @Mapping(source = "kyCongBo.id", target = "kyCongBoId")
    @Mapping(source = "coQuanChuTri.id", target = "coQuanChuTriId")
    @Mapping(source = "chiTieu.id", target = "chiTieuId")
    TieuChiDTO toDto(TieuChi tieuChi);

    @Mapping(target = "tieuChiBaoCaos", ignore = true)
    @Mapping(source = "kyCongBoId", target = "kyCongBo")
    @Mapping(source = "coQuanChuTriId", target = "coQuanChuTri")
    @Mapping(source = "chiTieuId", target = "chiTieu")
    TieuChi toEntity(TieuChiDTO tieuChiDTO);

    default TieuChi fromId(Long id) {
        if (id == null) {
            return null;
        }
        TieuChi tieuChi = new TieuChi();
        tieuChi.setId(id);
        return tieuChi;
    }
}
