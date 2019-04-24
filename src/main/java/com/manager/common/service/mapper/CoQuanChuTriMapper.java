package com.manager.common.service.mapper;

import com.manager.common.domain.*;
import com.manager.common.service.dto.CoQuanChuTriDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CoQuanChuTri and its DTO CoQuanChuTriDTO.
 */
@Mapper(componentModel = "spring", uses = {MaDinhDanhDonViMapper.class})
public interface CoQuanChuTriMapper extends EntityMapper<CoQuanChuTriDTO, CoQuanChuTri> {

    @Mapping(source = "maDinhDanhDonVi.id", target = "maDinhDanhDonViId")
    @Mapping(source = "maDinhDanhDonVi.name", target = "maDinhDanhDonViName")
    CoQuanChuTriDTO toDto(CoQuanChuTri coQuanChuTri);

    @Mapping(target = "tieuChis", ignore = true)
    @Mapping(source = "maDinhDanhDonViId", target = "maDinhDanhDonVi")
    CoQuanChuTri toEntity(CoQuanChuTriDTO coQuanChuTriDTO);

    default CoQuanChuTri fromId(Long id) {
        if (id == null) {
            return null;
        }
        CoQuanChuTri coQuanChuTri = new CoQuanChuTri();
        coQuanChuTri.setId(id);
        return coQuanChuTri;
    }
}
