package com.manager.common.service.mapper.mauphathanh;

import com.manager.common.domain.NoiDungDauRa;
import com.manager.common.service.dto.coquanchutri.NoiDungDauRaDetailDTO;
import com.manager.common.service.mapper.EntityMapper;
import com.manager.common.service.mapper.NhomDanhMucMapper;
import com.manager.common.service.mapper.NoiDungMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity NoiDungDauRa and its DTO NoiDungDauRaDTO.
 */
@Mapper(componentModel = "spring", uses = {NoiDungMapper.class, NhomDanhMucMapper.class, DanhMucDetailMapper.class})
public interface NoiDungDauRaDetailMapper extends EntityMapper<NoiDungDauRaDetailDTO, NoiDungDauRa> {

    @Mapping(source = "nhomDanhMuc.nhomDanhMucCode", target = "nhomDanhMucCode")
    @Mapping(source = "nhomDanhMuc.name", target = "nhomDanhMucName")
    @Mapping(source = "noiDungDauRa.nhomDanhMuc.danhMucs", target = "danhMucs")
    NoiDungDauRaDetailDTO toDto(NoiDungDauRa noiDungDauRa);

}
