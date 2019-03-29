package com.manager.common.service.mapper;

import com.manager.common.domain.*;
import com.manager.common.service.dto.ChiTieuDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity ChiTieu and its DTO ChiTieuDTO.
 */
@Mapper(componentModel = "spring", uses = {NhomChiTieuMapper.class})
public interface ChiTieuMapper extends EntityMapper<ChiTieuDTO, ChiTieu> {

    @Mapping(source = "nhomchitieu.id", target = "nhomchitieuId")
    ChiTieuDTO toDto(ChiTieu chiTieu);

    @Mapping(source = "nhomchitieuId", target = "nhomchitieu")
    ChiTieu toEntity(ChiTieuDTO chiTieuDTO);

    default ChiTieu fromId(Long id) {
        if (id == null) {
            return null;
        }
        ChiTieu chiTieu = new ChiTieu();
        chiTieu.setId(id);
        return chiTieu;
    }
}
