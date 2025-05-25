package web.backend.DTO;

import java.util.List;

public class SanPhamDTO {
    private Long maSP;
    private String tenSP;
    private String moTa;
    private String hinhAnh;
    private List<SanPhamChiTietDTO> chiTietList;

    // Getters and Setters
    public Long getMaSP() {
        return maSP;
    }

    public void setMaSP(Long maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public List<SanPhamChiTietDTO> getChiTietList() {
        return chiTietList;
    }

    public void setChiTietList(List<SanPhamChiTietDTO> chiTietList) {
        this.chiTietList = chiTietList;
    }
}

