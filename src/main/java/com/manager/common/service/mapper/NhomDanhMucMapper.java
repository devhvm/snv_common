package com.manager.common.service.mapper;

import com.manager.common.domain.*;
import com.manager.common.service.dto.NhomDanhMucDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity NhomDanhMuc and its DTO NhomDanhMucDTO.
 */
@Mapper(componentModel = "spring", uses = {TieuChiBaoCaoMapper.class})
public interface NhomDanhMucMapper extends EntityMapper<NhomDanhMucDTO, NhomDanhMuc> {

    @Mapping(source = "tieuChiBaoCao.id", target = "tieuChiBaoCaoId")
    NhomDanhMucDTO toDto(NhomDanhMuc nhomDanhMuc);

    @Mapping(target = "danhMucs", ignore = true)
    @Mapping(source = "tieuChiBaoCaoId", target = "tieuChiBaoCao")
    NhomDanhMuc toEntity(NhomDanhMucDTO nhomDanhMucDTO);

    default NhomDanhMuc fromId(Long id) {
        if (id == null) {
            return null;
        }
        NhomDanhMuc nhomDanhMuc = new NhomDanhMuc();
        nhomDanhMuc.setId(id);
        return nhomDanhMuc;
    }
}
