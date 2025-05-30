package web.backend.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class KhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maKH;

    private String hoTen;
    private String email;
    private String soDienThoai;
    private String matKhau;
    private LocalDate ngayDangKy;

    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'user'")
    private String role = "user";

    // Getters & Setters
    public Long getMaKH() { return maKH; }
    public void setMaKH(Long maKH) { this.maKH = maKH; }

    public String getHoTen() { return hoTen; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSoDienThoai() { return soDienThoai; }
    public void setSoDienThoai(String soDienThoai) { this.soDienThoai = soDienThoai; }

    public String getMatKhau() { return matKhau; }
    public void setMatKhau(String matKhau) { this.matKhau = matKhau; }

    public LocalDate getNgayDangKy() { return ngayDangKy; }
    public void setNgayDangKy(LocalDate ngayDangKy) { this.ngayDangKy = ngayDangKy; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
