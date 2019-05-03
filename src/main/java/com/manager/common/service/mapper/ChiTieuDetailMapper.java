package com.manager.common.service.mapper;

import com.manager.common.domain.ChiTieu;
import com.manager.common.service.dto.ChiTieuDTO;
import com.manager.common.service.dto.ChiTieuDetailDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity ChiTieu and its DTO ChiTieuDTO.
 */
@Mapper(componentModel = "spring", uses = {NhomChiTieuMapper.class})
public interface ChiTieuDetailMapper extends EntityMapper<ChiTieuDetailDTO, ChiTieu> {

}
