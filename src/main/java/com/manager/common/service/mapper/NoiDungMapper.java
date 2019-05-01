package com.manager.common.service.mapper;

import com.manager.common.domain.*;
import com.manager.common.service.dto.NoiDungDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity NoiDung and its DTO NoiDungDTO.
 */
@Mapper(componentModel = "spring", uses = {TieuChiMapper.class})
public interface NoiDungMapper extends EntityMapper<NoiDungDTO, NoiDung> {

    @Mapping(source = "tieuChi.id", target = "tieuChiId")
    NoiDungDTO toDto(NoiDung noiDung);

    @Mapping(source = "tieuChiId", target = "tieuChi")
    @Mapping(target = "noiDungDauVaos", ignore = true)
    @Mapping(target = "noiDungDauRas", ignore = true)
    NoiDung toEntity(NoiDungDTO noiDungDTO);

    default NoiDung fromId(Long id) {
        if (id == null) {
            return null;
        }
        NoiDung noiDung = new NoiDung();
        noiDung.setId(id);
        return noiDung;
    }
}
