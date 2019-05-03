package com.manager.common.service.mapper;

import com.manager.common.domain.CoQuanChuTri;
import com.manager.common.service.dto.CoQuanChuTriDTO;
import com.manager.common.service.dto.CoQuanChuTriDetailDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity CoQuanChuTri and its DTO CoQuanChuTriDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CoQuanChuTriDetailMapper extends EntityMapper<CoQuanChuTriDetailDTO, CoQuanChuTri> {

}
