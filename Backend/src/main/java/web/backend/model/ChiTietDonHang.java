package web.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class ChiTietDonHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maCT;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "maDH")
    private DonHang donHang;

    @ManyToOne
    @JoinColumn(name = "maSP")
    private SanPham sanPham;

    private int soLuong;
    private double donGia;
    private double thanhTien;
    private String kichThuoc;

    public ChiTietDonHang() {
    }

    public ChiTietDonHang(Long maCT, DonHang donHang, SanPham sanPham, int soLuong, double donGia, double thanhTien, String kichThuoc) {
        this.maCT = maCT;
        this.donHang = donHang;
        this.sanPham = sanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
        this.kichThuoc = kichThuoc;
    }

    public Long getMaCT() {
        return maCT;
    }

    public void setMaCT(Long maCT) {
        this.maCT = maCT;
    }

    public DonHang getDonHang() {
        return donHang;
    }

    public void setDonHang(DonHang donHang) {
        this.donHang = donHang;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    public String getKichThuoc() {
        return kichThuoc;
    }

    public void setKichThuoc(String kichThuoc) {
        this.kichThuoc = kichThuoc;
    }
}
