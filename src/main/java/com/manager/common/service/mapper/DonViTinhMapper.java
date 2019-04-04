package com.manager.common.service.mapper;

import com.manager.common.domain.*;
import com.manager.common.service.dto.DonViTinhDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity DonViTinh and its DTO DonViTinhDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DonViTinhMapper extends EntityMapper<DonViTinhDTO, DonViTinh> {


    @Mapping(target = "nhomphanloais", ignore = true)
    DonViTinh toEntity(DonViTinhDTO donViTinhDTO);

    default DonViTinh fromId(Long id) {
        if (id == null) {
            return null;
        }
        DonViTinh donViTinh = new DonViTinh();
        donViTinh.setId(id);
        return donViTinh;
    }
}
