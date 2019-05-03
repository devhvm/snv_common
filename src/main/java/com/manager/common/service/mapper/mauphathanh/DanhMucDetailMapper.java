package com.manager.common.service.mapper.mauphathanh;

import com.manager.common.domain.DanhMuc;
import com.manager.common.service.dto.coquanchutri.DanhMucDetailDTO;
import com.manager.common.service.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity DanhMuc and its DTO DanhMucDetailDTO.
 */
@Mapper(componentModel = "spring")
public interface DanhMucDetailMapper extends EntityMapper<DanhMucDetailDTO, DanhMuc> {

    @Mapping(source = "danhMuc.danhMucCode", target = "danhMucCode")
    @Mapping(source = "danhMuc.name", target = "name")
    DanhMucDetailDTO toDto(DanhMuc danhMuc);

}