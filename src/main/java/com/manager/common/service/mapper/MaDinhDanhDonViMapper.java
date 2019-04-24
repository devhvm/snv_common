package com.manager.common.service.mapper;

import com.manager.common.domain.*;
import com.manager.common.service.dto.MaDinhDanhDonViDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity MaDinhDanhDonVi and its DTO MaDinhDanhDonViDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MaDinhDanhDonViMapper extends EntityMapper<MaDinhDanhDonViDTO, MaDinhDanhDonVi> {


    @Mapping(target = "coQuanChuTris", ignore = true)
    MaDinhDanhDonVi toEntity(MaDinhDanhDonViDTO maDinhDanhDonViDTO);

    default MaDinhDanhDonVi fromId(Long id) {
        if (id == null) {
            return null;
        }
        MaDinhDanhDonVi maDinhDanhDonVi = new MaDinhDanhDonVi();
        maDinhDanhDonVi.setId(id);
        return maDinhDanhDonVi;
    }
}
