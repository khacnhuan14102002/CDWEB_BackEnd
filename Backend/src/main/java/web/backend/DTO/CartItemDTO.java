package web.backend.DTO;

import web.backend.model.CartItem;
import web.backend.model.SanPham;
import web.backend.model.SanPhamChiTiet;

public class CartItemDTO {
    private Long id;
    private Long maSP;
    private String tenSP;    // lấy từ SanPham
    private String kichCo;
    private int soLuong;
    private double gia;
    private String anh;
    private String userId;

    public CartItemDTO(Long id, Long maSP, String tenSP, String kichCo, int soLuong, double gia, String anh, String userId) {
        this.id = id;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.kichCo = kichCo;
        this.soLuong = soLuong;
        this.gia = gia;
        this.anh = anh;
        this.userId = userId;
    }

    public CartItemDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getKichCo() {
        return kichCo;
    }

    public void setKichCo(String kichCo) {
        this.kichCo = kichCo;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
