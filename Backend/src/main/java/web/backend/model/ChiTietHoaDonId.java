package web.backend.model;

import java.io.Serializable;
import java.util.Objects;

public class ChiTietHoaDonId implements Serializable {
    private Long donHang;
    private Long sanPham;

    public ChiTietHoaDonId() {}

    public ChiTietHoaDonId(Long donHang, Long sanPham) {
        this.donHang = donHang;
        this.sanPham = sanPham;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChiTietHoaDonId)) return false;
        ChiTietHoaDonId that = (ChiTietHoaDonId) o;
        return Objects.equals(donHang, that.donHang) && Objects.equals(sanPham, that.sanPham);
    }

    @Override
    public int hashCode() {
        return Objects.hash(donHang, sanPham);
    }
}
