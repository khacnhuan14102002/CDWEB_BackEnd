package web.backend.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class DonHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maDH;

    @ManyToOne
    @JoinColumn(name = "maKH")
    private KhachHang khachHang;

    private LocalDate ngayDat;
    private double tongTien;
    private String trangThai;
    private String phuongThucThanhToan;

    // Getters & Setters
    public Long getMaDH() { return maDH; }
    public void setMaDH(Long maDH) { this.maDH = maDH; }

    public KhachHang getKhachHang() { return khachHang; }
    public void setKhachHang(KhachHang khachHang) { this.khachHang = khachHang; }

    public LocalDate getNgayDat() { return ngayDat; }
    public void setNgayDat(LocalDate ngayDat) { this.ngayDat = ngayDat; }

    public double getTongTien() { return tongTien; }
    public void setTongTien(double tongTien) { this.tongTien = tongTien; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }

    public String getPhuongThucThanhToan() { return phuongThucThanhToan; }
    public void setPhuongThucThanhToan(String phuongThucThanhToan) { this.phuongThucThanhToan = phuongThucThanhToan; }
}
