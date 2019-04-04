package com.manager.common.service.mapper;

import com.manager.common.domain.*;
import com.manager.common.service.dto.DonViDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity DonVi and its DTO DonViDTO.
 */
@Mapper(componentModel = "spring", uses = {PhamViMapper.class})
public interface DonViMapper extends EntityMapper<DonViDTO, DonVi> {

    @Mapping(source = "phamvi.id", target = "phamviId")
    DonViDTO toDto(DonVi donVi);

    @Mapping(source = "phamviId", target = "phamvi")
    @Mapping(target = "nhomphanloais", ignore = true)
    DonVi toEntity(DonViDTO donViDTO);

    default DonVi fromId(Long id) {
        if (id == null) {
            return null;
        }
        DonVi donVi = new DonVi();
        donVi.setId(id);
        return donVi;
    }
}
