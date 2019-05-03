package com.manager.common.service.mapper;

import com.manager.common.domain.KyCongBo;
import com.manager.common.service.dto.KyCongBoDTO;
import com.manager.common.service.dto.KyCongBoDetailDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity KyCongBo and its DTO KyCongBoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface KyCongBoDetailMapper extends EntityMapper<KyCongBoDetailDTO, KyCongBo> {
}
