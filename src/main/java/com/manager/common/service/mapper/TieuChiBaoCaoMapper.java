package com.manager.common.service.mapper;

import com.manager.common.domain.TieuChiBaoCao;
import com.manager.common.service.dto.TieuChiBaoCaoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity TieuChiBaoCao and its DTO TieuChiBaoCaoDTO.
 */
@Mapper(componentModel = "spring", uses = {NhomDanhMucMapper.class, TieuChiMapper.class})
public interface TieuChiBaoCaoMapper extends EntityMapper<TieuChiBaoCaoDTO, TieuChiBaoCao> {

    @Mapping(source = "nhomDanhMuc.id", target = "nhomDanhMucId")
    @Mapping(source = "tieuChi.id", target = "tieuChiId")
    TieuChiBaoCaoDTO toDto(TieuChiBaoCao tieuChiBaoCao);

    @Mapping(source = "nhomDanhMucId", target = "nhomDanhMuc")
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
