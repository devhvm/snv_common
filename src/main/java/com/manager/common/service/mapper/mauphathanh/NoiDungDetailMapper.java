package com.manager.common.service.mapper.mauphathanh;

import com.manager.common.domain.NoiDung;
import com.manager.common.service.dto.coquanchutri.NoiDungDetailDTO;
import com.manager.common.service.mapper.EntityMapper;
import com.manager.common.service.mapper.TieuChiMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity NoiDung and its DTO NoiDungDTO.
 */
@Mapper(componentModel = "spring", uses = {TieuChiMapper.class, NoiDungDauRaDetailMapper.class, NoiDungDauVaoDetailMapper.class})
public interface NoiDungDetailMapper extends EntityMapper<NoiDungDetailDTO, NoiDung> {

    @Mapping(target = "noiDungDauVaos")
    @Mapping(target = "noiDungDauRas")
    NoiDungDetailDTO toDto(NoiDung noiDung);

}
