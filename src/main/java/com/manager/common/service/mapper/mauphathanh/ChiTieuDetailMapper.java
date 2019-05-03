package com.manager.common.service.mapper.mauphathanh;

import com.manager.common.domain.ChiTieu;
import com.manager.common.service.dto.coquanchutri.ChiTieuDetailDTO;
import com.manager.common.service.mapper.EntityMapper;
import com.manager.common.service.mapper.NhomChiTieuMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity ChiTieu and its DTO ChiTieuDTO.
 */
@Mapper(componentModel = "spring", uses = {NhomChiTieuMapper.class})
public interface ChiTieuDetailMapper extends EntityMapper<ChiTieuDetailDTO, ChiTieu> {

}
