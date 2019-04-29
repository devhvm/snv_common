package com.manager.common.service.mapper;

import com.manager.common.domain.NhomChiTieu;
import com.manager.common.service.dto.NhomChiTieuDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity NhomChiTieu and its DTO NhomChiTieuDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface NhomChiTieuMapper extends EntityMapper<NhomChiTieuDTO, NhomChiTieu> {


    @Mapping(target = "chiTieus", ignore = true)
    NhomChiTieu toEntity(NhomChiTieuDTO nhomChiTieuDTO);

    default NhomChiTieu fromId(Long id) {
        if (id == null) {
            return null;
        }
        NhomChiTieu nhomChiTieu = new NhomChiTieu();
        nhomChiTieu.setId(id);
        return nhomChiTieu;
    }
}
