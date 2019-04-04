package com.manager.common.service.mapper;

import com.manager.common.domain.*;
import com.manager.common.service.dto.NoiDungDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity NoiDung and its DTO NoiDungDTO.
 */
@Mapper(componentModel = "spring", uses = {NhomNoiDungMapper.class})
public interface NoiDungMapper extends EntityMapper<NoiDungDTO, NoiDung> {

    @Mapping(source = "nhomnoidung.id", target = "nhomnoidungId")
    NoiDungDTO toDto(NoiDung noiDung);

    @Mapping(source = "nhomnoidungId", target = "nhomnoidung")
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
