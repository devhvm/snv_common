package com.manager.common.service.mapper.mauphathanh;

import com.manager.common.domain.KyCongBo;
import com.manager.common.service.dto.coquanchutri.KyCongBoDetailDTO;
import com.manager.common.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity KyCongBo and its DTO KyCongBoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface KyCongBoDetailMapper extends EntityMapper<KyCongBoDetailDTO, KyCongBo> {
}
