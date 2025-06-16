package web.backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import web.backend.model.ChiTietDonHang;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Map;

public interface ChiTietDonHangRepository extends JpaRepository<ChiTietDonHang, Long> {
    List<ChiTietDonHang> findByDonHang_MaDH(Long maDH);

    @Query("SELECT c.sanPham, SUM(c.soLuong) as totalSold " +
            "FROM ChiTietDonHang c " +
            "GROUP BY c.sanPham " +
            "ORDER BY totalSold DESC")
    List<Object[]> findTop5BestSellingProducts(Pageable pageable);
// thong ke

    @Query("SELECT c.sanPham.tenSP, SUM(c.soLuong) as totalQuantity " +
            "FROM ChiTietDonHang c " +
            "GROUP BY c.sanPham.tenSP " +
            "ORDER BY totalQuantity DESC")
    List<Object[]> getBestSellingProducts();

    // Lấy top sản phẩm bán chạy theo số lần đặt hoặc số lượng bán
    @Query("SELECT new map(" +
            "c.sanPham.maSP as maSP, " +
            "c.sanPham.tenSP as tenSP, " +
            "c.sanPham.hinhAnh as hinhAnh, " +
            "SUM(c.soLuong) as soLuongBan, " +
            "COUNT(DISTINCT c.donHang.maDH) as soLanDat) " +
            "FROM ChiTietDonHang c " +
            "GROUP BY c.sanPham.maSP, c.sanPham.tenSP, c.sanPham.hinhAnh " +
            "ORDER BY soLanDat DESC, soLuongBan DESC")
    List<Map<String, Object>> findTopProductsByOrderCountOrQuantity(Pageable pageable);
}

