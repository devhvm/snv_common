package com.manager.common.service.mapper;

import com.manager.common.domain.NoiDungDauRa;
import com.manager.common.service.dto.NoiDungDauRaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity NoiDungDauRa and its DTO NoiDungDauRaDTO.
 */
@Mapper(componentModel = "spring", uses = {NoiDungMapper.class, NhomDanhMucMapper.class})
public interface NoiDungDauRaMapper extends EntityMapper<NoiDungDauRaDTO, NoiDungDauRa> {

    @Mapping(source = "noiDung.id", target = "noiDungId")
    @Mapping(source = "nhomDanhMuc.id", target = "nhomDanhMucId")
    NoiDungDauRaDTO toDto(NoiDungDauRa noiDungDauRa);

    @Mapping(source = "noiDungId", target = "noiDung")
    @Mapping(source = "nhomDanhMucId", target = "nhomDanhMuc")
    NoiDungDauRa toEntity(NoiDungDauRaDTO noiDungDauRaDTO);

    default NoiDungDauRa fromId(Long id) {
        if (id == null) {
            return null;
        }
        NoiDungDauRa noiDungDauRa = new NoiDungDauRa();
        noiDungDauRa.setId(id);
        return noiDungDauRa;
    }
}
