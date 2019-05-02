package com.manager.common.service.mapper;

import com.manager.common.domain.NhomDanhMuc;
import com.manager.common.service.dto.NhomDanhMucDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity NhomDanhMuc and its DTO NhomDanhMucDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface NhomDanhMucMapper extends EntityMapper<NhomDanhMucDTO, NhomDanhMuc> {


    @Mapping(target = "danhMucs", ignore = true)
    NhomDanhMuc toEntity(NhomDanhMucDTO nhomDanhMucDTO);

    default NhomDanhMuc fromId(Long id) {
        if (id == null) {
            return null;
        }
        NhomDanhMuc nhomDanhMuc = new NhomDanhMuc();
        nhomDanhMuc.setId(id);
        return nhomDanhMuc;
    }
}
