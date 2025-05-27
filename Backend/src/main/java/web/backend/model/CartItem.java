package web.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "gio_hang")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long maSP;
    private String kichCo;
    private int soLuong = 1; // mặc định thêm 1 sản phẩm
    private double gia;
    private String anh;
    // Bạn có thể thêm userId nếu có hệ thống người dùng
    private String userId;

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    // Constructors
    public CartItem() {
    }

    public CartItem(Long id, Long maSP, String kichCo, int soLuong, double gia, String anh, String userId) {
        this.id = id;
        this.maSP = maSP;
        this.kichCo = kichCo;
        this.soLuong = soLuong;
        this.gia = gia;
        this.anh = anh;
        this.userId = userId;
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

    public String getKichCo() {
        return kichCo;
    }

    public void setKichCo(String kichCo) {
        this.kichCo = kichCo;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}