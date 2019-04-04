package com.manager.common.service.mapper;

import com.manager.common.domain.*;
import com.manager.common.service.dto.NhomNoiDungDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity NhomNoiDung and its DTO NhomNoiDungDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface NhomNoiDungMapper extends EntityMapper<NhomNoiDungDTO, NhomNoiDung> {


    @Mapping(target = "noidungs", ignore = true)
    NhomNoiDung toEntity(NhomNoiDungDTO nhomNoiDungDTO);

    default NhomNoiDung fromId(Long id) {
        if (id == null) {
            return null;
        }
        NhomNoiDung nhomNoiDung = new NhomNoiDung();
        nhomNoiDung.setId(id);
        return nhomNoiDung;
    }
}
