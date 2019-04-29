package com.manager.common.service.mapper;

import com.manager.common.domain.DonViTinh;
import com.manager.common.service.dto.DonViTinhDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity DonViTinh and its DTO DonViTinhDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DonViTinhMapper extends EntityMapper<DonViTinhDTO, DonViTinh> {


    @Mapping(target = "nhomPhanLoais", ignore = true)
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
