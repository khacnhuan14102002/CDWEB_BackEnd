package web.backend.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class SanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maSP;
    private String tenSP;
    private String moTa;
    private String hinhAnh;

    @ManyToOne
    @JoinColumn(name = "maDanhMuc")
    private DanhMuc danhMuc;

    @ManyToOne
    @JoinColumn(name = "maType")
    private Type type;

    @OneToMany(mappedBy = "sanPham", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SanPhamChiTiet> chiTietList = new ArrayList<>();
    public SanPham() {
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

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public DanhMuc getDanhMuc() {
        return danhMuc;
    }

    public void setDanhMuc(DanhMuc danhMuc) {
        this.danhMuc = danhMuc;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<SanPhamChiTiet> getChiTietList() {
        return chiTietList;
    }

    public void setChiTietList(List<SanPhamChiTiet> chiTietList) {
        this.chiTietList.clear();
        if (chiTietList != null) {
            for (SanPhamChiTiet chiTiet : chiTietList) {
                this.addChiTiet(chiTiet);
            }
        }
    }


    public void addChiTiet(SanPhamChiTiet chiTiet) {
        this.chiTietList.add(chiTiet);
        chiTiet.setSanPham(this);
    }

    public void removeChiTiet(SanPhamChiTiet chiTiet) {
        this.chiTietList.remove(chiTiet);
        chiTiet.setSanPham(null); // Bỏ liên kết
    }
}
