package com.manager.common.service.mapper.mauphathanh;

import com.manager.common.domain.NoiDungDauVao;
import com.manager.common.service.dto.coquanchutri.NoiDungDauVaoDetailDTO;
import com.manager.common.service.mapper.EntityMapper;
import com.manager.common.service.mapper.NhomDanhMucMapper;
import com.manager.common.service.mapper.NoiDungMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity NoiDungDauRa and its DTO NoiDungDauVaoDetailDTO.
 */
@Mapper(componentModel = "spring", uses = {NoiDungMapper.class, NhomDanhMucMapper.class, DanhMucDetailMapper.class})
public interface NoiDungDauVaoDetailMapper extends EntityMapper<NoiDungDauVaoDetailDTO, NoiDungDauVao> {

    @Mapping(source = "nhomDanhMuc.nhomDanhMucCode", target = "nhomDanhMucCode")
    @Mapping(source = "nhomDanhMuc.name", target = "nhomDanhMucName")
    @Mapping(source = "noiDungDauVao.nhomDanhMuc.danhMucs", target = "danhMucs")
    NoiDungDauVaoDetailDTO toDto(NoiDungDauVao noiDungDauVao);

}
