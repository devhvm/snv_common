package com.manager.common.service.mapper;

import com.manager.common.domain.*;
import com.manager.common.service.dto.TieuChiBaoCaoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TieuChiBaoCao and its DTO TieuChiBaoCaoDTO.
 */
@Mapper(componentModel = "spring", uses = {TieuChiMapper.class})
public interface TieuChiBaoCaoMapper extends EntityMapper<TieuChiBaoCaoDTO, TieuChiBaoCao> {

    @Mapping(source = "tieuChi.id", target = "tieuChiId")
    TieuChiBaoCaoDTO toDto(TieuChiBaoCao tieuChiBaoCao);

    @Mapping(target = "nhomDanhMucs", ignore = true)
    @Mapping(source = "tieuChiId", target = "tieuChi")
    TieuChiBaoCao toEntity(TieuChiBaoCaoDTO tieuChiBaoCaoDTO);

    default TieuChiBaoCao fromId(Long id) {
        if (id == null) {
            return null;
        }
        TieuChiBaoCao tieuChiBaoCao = new TieuChiBaoCao();
        tieuChiBaoCao.setId(id);
        return tieuChiBaoCao;
    }
}
