package com.manager.common.service.mapper;

import com.manager.common.domain.*;
import com.manager.common.service.dto.NhomPhanLoaiDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity NhomPhanLoai and its DTO NhomPhanLoaiDTO.
 */
@Mapper(componentModel = "spring", uses = {DonViMapper.class})
public interface NhomPhanLoaiMapper extends EntityMapper<NhomPhanLoaiDTO, NhomPhanLoai> {

    @Mapping(source = "donvi.id", target = "donviId")
    NhomPhanLoaiDTO toDto(NhomPhanLoai nhomPhanLoai);

    @Mapping(target = "doituongs", ignore = true)
    @Mapping(source = "donviId", target = "donvi")
    NhomPhanLoai toEntity(NhomPhanLoaiDTO nhomPhanLoaiDTO);

    default NhomPhanLoai fromId(Long id) {
        if (id == null) {
            return null;
        }
        NhomPhanLoai nhomPhanLoai = new NhomPhanLoai();
        nhomPhanLoai.setId(id);
        return nhomPhanLoai;
    }
}
