package web.backend.DTO;

import java.util.List;

public class SanPhamDTO {
    private Long maSP;
    private String tenSP;
    private String moTa;
    private String hinhAnh;

    private Long maDanhMuc;
    private String tenDanhMuc;

    private Long maType;
    private String tenType;

    private List<SanPhamChiTietDTO> chiTietList;

    public SanPhamDTO() {}

    public SanPhamDTO(Long maSP, String tenSP, String moTa, String hinhAnh, Long maDanhMuc, String tenDanhMuc, Long maType, String tenType, List<SanPhamChiTietDTO> chiTietList) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.moTa = moTa;
        this.hinhAnh = hinhAnh;
        this.maDanhMuc = maDanhMuc;
        this.tenDanhMuc = tenDanhMuc;
        this.maType = maType;
        this.tenType = tenType;
        this.chiTietList = chiTietList;
    }

    public Long getMaSP() { return maSP; }
    public void setMaSP(Long maSP) { this.maSP = maSP; }

    public String getTenSP() { return tenSP; }
    public void setTenSP(String tenSP) { this.tenSP = tenSP; }

    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }

    public String getHinhAnh() { return hinhAnh; }
    public void setHinhAnh(String hinhAnh) { this.hinhAnh = hinhAnh; }

    public Long getMaDanhMuc() { return maDanhMuc; }
    public void setMaDanhMuc(Long maDanhMuc) { this.maDanhMuc = maDanhMuc; }
    public String getTenDanhMuc() { return tenDanhMuc; }
    public void setTenDanhMuc(String tenDanhMuc) { this.tenDanhMuc = tenDanhMuc; }

    public Long getMaType() { return maType; }
    public void setMaType(Long maType) { this.maType = maType; }
    public String getTenType() { return tenType; }
    public void setTenType(String tenType) { this.tenType = tenType; }

    public List<SanPhamChiTietDTO> getChiTietList() { return chiTietList; }
    public void setChiTietList(List<SanPhamChiTietDTO> chiTietList) { this.chiTietList = chiTietList; }
}

