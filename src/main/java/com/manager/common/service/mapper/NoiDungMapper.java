package com.manager.common.service.mapper;

import com.manager.common.domain.NoiDung;
import com.manager.common.service.dto.NoiDungDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity NoiDung and its DTO NoiDungDTO.
 */
@Mapper(componentModel = "spring", uses = {ChiTieuMapper.class})
public interface NoiDungMapper extends EntityMapper<NoiDungDTO, NoiDung> {

    @Mapping(source = "noiDung.id", target = "noiDungId")
    @Mapping(source = "noiDung.chiTieuCode", target = "noiDungChiTieuCode")
    NoiDungDTO toDto(NoiDung noiDung);

    @Mapping(source = "noiDungId", target = "noiDung")
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
