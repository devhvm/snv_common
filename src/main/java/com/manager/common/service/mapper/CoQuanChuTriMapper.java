package com.manager.common.service.mapper;

import com.manager.common.domain.CoQuanChuTri;
import com.manager.common.service.dto.CoQuanChuTriDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity CoQuanChuTri and its DTO CoQuanChuTriDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CoQuanChuTriMapper extends EntityMapper<CoQuanChuTriDTO, CoQuanChuTri> {



    default CoQuanChuTri fromId(Long id) {
        if (id == null) {
            return null;
        }
        CoQuanChuTri coQuanChuTri = new CoQuanChuTri();
        coQuanChuTri.setId(id);
        return coQuanChuTri;
    }
}
