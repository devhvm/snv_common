package com.manager.common.service.mapper;

import com.manager.common.domain.*;
import com.manager.common.service.dto.PhamViDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PhamVi and its DTO PhamViDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PhamViMapper extends EntityMapper<PhamViDTO, PhamVi> {


    @Mapping(target = "donvi", ignore = true)
    PhamVi toEntity(PhamViDTO phamViDTO);

    default PhamVi fromId(Long id) {
        if (id == null) {
            return null;
        }
        PhamVi phamVi = new PhamVi();
        phamVi.setId(id);
        return phamVi;
    }
}
