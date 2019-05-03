package com.manager.common.service.mapper;

import com.manager.common.domain.TieuChi;
import com.manager.common.service.dto.TieuChiDTO;
import com.manager.common.service.dto.TieuChiDetailDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity TieuChi and its DTO TieuChiDetailDTO.
 */
@Mapper(componentModel = "spring", uses = {KyCongBoDetailMapper.class, CoQuanChuTriDetailMapper.class, ChiTieuDetailMapper.class, NoiDungDetailMapper.class})
public interface TieuChiDetailMapper extends EntityMapper<TieuChiDetailDTO, TieuChi> {

    @Mapping(target = "noiDungs")
    TieuChiDetailDTO toDto(TieuChi tieuChi);
}
