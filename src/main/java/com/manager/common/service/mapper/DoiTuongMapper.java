package com.manager.common.service.mapper;

import com.manager.common.domain.*;
import com.manager.common.service.dto.DoiTuongDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity DoiTuong and its DTO DoiTuongDTO.
 */
@Mapper(componentModel = "spring", uses = {NhomPhanLoaiMapper.class})
public interface DoiTuongMapper extends EntityMapper<DoiTuongDTO, DoiTuong> {

    @Mapping(source = "nhomphanloai.id", target = "nhomphanloaiId")
    DoiTuongDTO toDto(DoiTuong doiTuong);

    @Mapping(source = "nhomphanloaiId", target = "nhomphanloai")
    DoiTuong toEntity(DoiTuongDTO doiTuongDTO);

    default DoiTuong fromId(Long id) {
        if (id == null) {
            return null;
        }
        DoiTuong doiTuong = new DoiTuong();
        doiTuong.setId(id);
        return doiTuong;
    }
}
