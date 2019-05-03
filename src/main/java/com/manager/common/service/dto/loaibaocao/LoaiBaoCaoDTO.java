package com.manager.common.service.dto.loaibaocao;

import com.manager.common.domain.enumeration.Status;
import com.manager.common.service.dto.AbstractAuditingDTO;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the NhomPhanLoai entity.
 */
public class LoaiBaoCaoDTO implements Serializable {

    @NotNull
    private String loaiBaoCaoCode;

    @NotNull
    private String name;

    private DonViTinhDetailDTO donViTinh;

    private List<DoiTuongDetailDTO> doiTuongs = new ArrayList<>();

    public String getLoaiBaoCaoCode() {
        return loaiBaoCaoCode;
    }

    public void setLoaiBaoCaoCode(String loaiBaoCaoCode) {
        this.loaiBaoCaoCode = loaiBaoCaoCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DonViTinhDetailDTO getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(DonViTinhDetailDTO donViTinh) {
        this.donViTinh = donViTinh;
    }

    public List<DoiTuongDetailDTO> getDoiTuongs() {
        return doiTuongs;
    }

    public void setDoiTuongs(List<DoiTuongDetailDTO> doiTuongs) {
        this.doiTuongs = doiTuongs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LoaiBaoCaoDTO nhomPhanLoaiDTO = (LoaiBaoCaoDTO) o;
        if (nhomPhanLoaiDTO.getLoaiBaoCaoCode() == null || getLoaiBaoCaoCode() == null) {
            return false;
        }
        return Objects.equals(getLoaiBaoCaoCode(), nhomPhanLoaiDTO.getLoaiBaoCaoCode());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getLoaiBaoCaoCode());
    }

}
