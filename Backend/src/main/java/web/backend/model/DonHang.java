package web.backend.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

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
    private String diaChi;
    // Getters & Setters
    @OneToMany(mappedBy = "donHang", cascade = CascadeType.ALL)
    private List<ChiTietDonHang> chiTietDonHang;

    public DonHang() {
    }

    public DonHang(Long maDH, KhachHang khachHang, LocalDate ngayDat, double tongTien, String trangThai, String phuongThucThanhToan, String diaChi, List<ChiTietDonHang> chiTietDonHang) {
        this.maDH = maDH;
        this.khachHang = khachHang;
        this.ngayDat = ngayDat;
        this.tongTien = tongTien;
        this.trangThai = trangThai;
        this.phuongThucThanhToan = phuongThucThanhToan;
        this.diaChi = diaChi;
        this.chiTietDonHang = chiTietDonHang;
    }

    public List<ChiTietDonHang> getChiTietDonHang() {
        return chiTietDonHang;
    }

    public void setChiTietDonHang(List<ChiTietDonHang> chiTietDonHang) {
        this.chiTietDonHang = chiTietDonHang;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

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
