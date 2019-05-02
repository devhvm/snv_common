package com.manager.common.service.mapper;

import com.manager.common.domain.NoiDung;
import com.manager.common.service.dto.NoiDungDTO;
import com.manager.common.service.dto.NoiDungDetailDTO;
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
