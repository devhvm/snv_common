package com.manager.common.service.mapper;

import com.manager.common.domain.ChiTieu;
import com.manager.common.service.dto.ChiTieuDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity ChiTieu and its DTO ChiTieuDTO.
 */
@Mapper(componentModel = "spring", uses = {NhomChiTieuMapper.class})
public interface ChiTieuMapper extends EntityMapper<ChiTieuDTO, ChiTieu> {

    @Mapping(source = "nhomChiTieu.id", target = "nhomChiTieuId")
    @Mapping(source = "nhomChiTieu.name", target = "nhomChiTieuName")
    ChiTieuDTO toDto(ChiTieu chiTieu);

    @Mapping(target = "tieuChis", ignore = true)
    @Mapping(source = "nhomChiTieuId", target = "nhomChiTieu")
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
