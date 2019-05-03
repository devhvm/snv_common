package com.manager.common.service.mapper.mauphathanh;

import com.manager.common.domain.CoQuanChuTri;
import com.manager.common.service.dto.coquanchutri.CoQuanChuTriDetailDTO;
import com.manager.common.service.mapper.EntityMapper;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity CoQuanChuTri and its DTO CoQuanChuTriDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CoQuanChuTriDetailMapper extends EntityMapper<CoQuanChuTriDetailDTO, CoQuanChuTri> {

}
