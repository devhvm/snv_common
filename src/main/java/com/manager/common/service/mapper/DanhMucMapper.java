package com.manager.common.service.mapper;

import com.manager.common.domain.*;
import com.manager.common.service.dto.DanhMucDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity DanhMuc and its DTO DanhMucDTO.
 */
@Mapper(componentModel = "spring", uses = {NhomDanhMucMapper.class})
public interface DanhMucMapper extends EntityMapper<DanhMucDTO, DanhMuc> {

    @Mapping(source = "nhomDanhMuc.id", target = "nhomDanhMucId")
    @Mapping(source = "nhomDanhMuc.name", target = "nhomDanhMucName")
    DanhMucDTO toDto(DanhMuc danhMuc);

    @Mapping(source = "nhomDanhMucId", target = "nhomDanhMuc")
    DanhMuc toEntity(DanhMucDTO danhMucDTO);

    default DanhMuc fromId(Long id) {
        if (id == null) {
            return null;
        }
        DanhMuc danhMuc = new DanhMuc();
        danhMuc.setId(id);
        return danhMuc;
    }
}
