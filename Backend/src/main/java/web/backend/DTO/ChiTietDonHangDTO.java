package web.backend.DTO;

public class ChiTietDonHangDTO {
    private Long maDH;
    private Long maSP;
    private int soLuong;
    private double donGia;
    private double thanhTien;
    private String kichThuoc;

    public ChiTietDonHangDTO() {
    }

    public ChiTietDonHangDTO(Long maDH, Long maSP, int soLuong, double donGia, double thanhTien, String kichThuoc) {
        this.maDH = maDH;
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
        this.kichThuoc = kichThuoc;
    }

    public Long getMaDH() {
        return maDH;
    }

    public void setMaDH(Long maDH) {
        this.maDH = maDH;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Long getMaSP() {
        return maSP;
    }

    public void setMaSP(Long maSP) {
        this.maSP = maSP;
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
