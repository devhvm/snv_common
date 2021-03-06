package com.manager.common.service.mapper;

import com.manager.common.domain.*;
import com.manager.common.service.dto.DanhMucDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity DanhMuc and its DTO DanhMucDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DanhMucMapper extends EntityMapper<DanhMucDTO, DanhMuc> {



    default DanhMuc fromId(Long id) {
        if (id == null) {
            return null;
        }
        DanhMuc danhMuc = new DanhMuc();
        danhMuc.setId(id);
        return danhMuc;
    }
}
