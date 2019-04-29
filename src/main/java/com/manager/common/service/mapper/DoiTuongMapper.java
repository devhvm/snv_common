package com.manager.common.service.mapper;

import com.manager.common.domain.DoiTuong;
import com.manager.common.service.dto.DoiTuongDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for the entity DoiTuong and its DTO DoiTuongDTO.
 */
@Mapper(componentModel = "spring", uses = {NhomPhanLoaiMapper.class})
public interface DoiTuongMapper extends EntityMapper<DoiTuongDTO, DoiTuong> {

    @Mapping(source = "nhomPhanLoai.id", target = "nhomPhanLoaiId")
    @Mapping(source = "nhomPhanLoai.nhomPhanLoaiCode", target = "nhomPhanLoaiNhomPhanLoaiCode")
    DoiTuongDTO toDto(DoiTuong doiTuong);

    @Mapping(source = "nhomPhanLoaiId", target = "nhomPhanLoai")
    DoiTuong toEntity(DoiTuongDTO doiTuongDTO);

    default DoiTuong fromId(Long id) {
        if (id == null) {
            return null;
        }
        DoiTuong doiTuong = new DoiTuong();
        doiTuong.setId(id);
        return doiTuong;
    }
}
