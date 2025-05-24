package web.backend.model;

import jakarta.persistence.*;

@Entity
public class DanhMuc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maDanhMuc;

    private String tenDanhMuc;

    // Getters & Setters
    public Long getMaDanhMuc() { return maDanhMuc; }
    public void setMaDanhMuc(Long maDanhMuc) { this.maDanhMuc = maDanhMuc; }

    public String getTenDanhMuc() { return tenDanhMuc; }
    public void setTenDanhMuc(String tenDanhMuc) { this.tenDanhMuc = tenDanhMuc; }
}
