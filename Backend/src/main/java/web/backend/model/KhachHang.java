package web.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
//import org.hibernate.annotations.processing.Pattern;
import jakarta.validation.constraints.Pattern;


import java.time.LocalDate;
import java.util.List;

@Entity
public class KhachHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maKH;

    @NotBlank(message = "Họ tên không được để trống")
    @Column(name = "ho_ten")
    private String hoTen;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ")
    private String email;

    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "^\\d{4,10}$", message = "Số điện thoại phải từ 4 đến 10 chữ số")
    private String soDienThoai;

    private String diaChi;

    @NotBlank(message = "Mật khẩu không được để trống")
    @Size(min = 1, message = "Mật khẩu phải ít nhất 6 ký tự")
    private String matKhau;

    @PastOrPresent(message = "Ngày đăng ký không được lớn hơn ngày hiện tại")
    private LocalDate ngayDangKy;

    @OneToMany(mappedBy = "khachHang", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DonHang> donHangs;

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
