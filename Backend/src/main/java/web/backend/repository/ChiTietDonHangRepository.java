package web.backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import web.backend.model.ChiTietDonHang;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ChiTietDonHangRepository extends JpaRepository<ChiTietDonHang, Long> {
    List<ChiTietDonHang> findByDonHang_MaDH(Long maDH);
    @Query("SELECT c.sanPham, SUM(c.soLuong) as totalSold " +
            "FROM ChiTietDonHang c " +
            "GROUP BY c.sanPham " +
            "ORDER BY totalSold DESC")
    List<Object[]> findTop5BestSellingProducts(Pageable pageable);

}
