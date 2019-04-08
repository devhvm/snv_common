package com.manager.common.service.mapper;

import com.manager.common.domain.*;
import com.manager.common.service.dto.NhomPhanLoaiDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity NhomPhanLoai and its DTO NhomPhanLoaiDTO.
 */
@Mapper(componentModel = "spring", uses = {DonViTinhMapper.class})
public interface NhomPhanLoaiMapper extends EntityMapper<NhomPhanLoaiDTO, NhomPhanLoai> {

    @Mapping(source = "donvitinh.id", target = "donvitinhId")
    @Mapping(source = "donvitinh.donViTinhCode", target = "donvitinhDonViTinhCode")
    NhomPhanLoaiDTO toDto(NhomPhanLoai nhomPhanLoai);

    @Mapping(target = "doituongs", ignore = true)
    @Mapping(source = "donvitinhId", target = "donvitinh")
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
