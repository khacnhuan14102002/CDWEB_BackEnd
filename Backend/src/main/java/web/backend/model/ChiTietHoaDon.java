package web.backend.model;

import jakarta.persistence.*;

@Entity
@IdClass(ChiTietHoaDonId.class)
public class ChiTietHoaDon {
    @Id
    @ManyToOne
    @JoinColumn(name = "maDH")
    private DonHang donHang;

    @Id
    @ManyToOne
    @JoinColumn(name = "maSP")
    private SanPham sanPham;

    private int soLuong;
    private double donGia;

    // Getters & Setters
    public DonHang getDonHang() { return donHang; }
    public void setDonHang(DonHang donHang) { this.donHang = donHang; }

    public SanPham getSanPham() { return sanPham; }
    public void setSanPham(SanPham sanPham) { this.sanPham = sanPham; }

    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }

    public double getDonGia() { return donGia; }
    public void setDonGia(double donGia) { this.donGia = donGia; }
}
