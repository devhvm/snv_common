package com.manager.common.service.mapper;

import com.manager.common.domain.NoiDungDauVao;
import com.manager.common.service.dto.NoiDungDauVaoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity NoiDungDauVao and its DTO NoiDungDauVaoDTO.
 */
@Mapper(componentModel = "spring", uses = {NoiDungMapper.class, NhomDanhMucMapper.class})
public interface NoiDungDauVaoMapper extends EntityMapper<NoiDungDauVaoDTO, NoiDungDauVao> {

    @Mapping(source = "noiDung.id", target = "noiDungId")
    @Mapping(source = "nhomDanhMuc.id", target = "nhomDanhMucId")
    NoiDungDauVaoDTO toDto(NoiDungDauVao noiDungDauVao);

    @Mapping(source = "noiDungId", target = "noiDung")
    @Mapping(source = "nhomDanhMucId", target = "nhomDanhMuc")
    NoiDungDauVao toEntity(NoiDungDauVaoDTO noiDungDauVaoDTO);

    default NoiDungDauVao fromId(Long id) {
        if (id == null) {
            return null;
        }
        NoiDungDauVao noiDungDauVao = new NoiDungDauVao();
        noiDungDauVao.setId(id);
        return noiDungDauVao;
    }
}
