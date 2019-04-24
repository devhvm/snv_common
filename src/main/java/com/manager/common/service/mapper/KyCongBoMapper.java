package com.manager.common.service.mapper;

import com.manager.common.domain.*;
import com.manager.common.service.dto.KyCongBoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity KyCongBo and its DTO KyCongBoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface KyCongBoMapper extends EntityMapper<KyCongBoDTO, KyCongBo> {


    @Mapping(target = "tieuChis", ignore = true)
    KyCongBo toEntity(KyCongBoDTO kyCongBoDTO);

    default KyCongBo fromId(Long id) {
        if (id == null) {
            return null;
        }
        KyCongBo kyCongBo = new KyCongBo();
        kyCongBo.setId(id);
        return kyCongBo;
    }
}
