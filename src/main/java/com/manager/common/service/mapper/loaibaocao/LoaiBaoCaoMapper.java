package com.manager.common.service.mapper.loaibaocao;

import com.manager.common.domain.NhomPhanLoai;
import com.manager.common.service.dto.NhomPhanLoaiDTO;
import com.manager.common.service.dto.loaibaocao.LoaiBaoCaoDTO;
import com.manager.common.service.mapper.DonViTinhMapper;
import com.manager.common.service.mapper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity NhomPhanLoai and its DTO NhomPhanLoaiDTO.
 */
@Mapper(componentModel = "spring", uses = {DonViTinhMapper.class})
public interface LoaiBaoCaoMapper extends EntityMapper<LoaiBaoCaoDTO, NhomPhanLoai> {

    @Mapping(target = "doiTuongs")
    @Mapping(source = "nhomPhanLoaiCode", target = "loaiBaoCaoCode")
    LoaiBaoCaoDTO toDto(NhomPhanLoai nhomPhanLoai);
}
