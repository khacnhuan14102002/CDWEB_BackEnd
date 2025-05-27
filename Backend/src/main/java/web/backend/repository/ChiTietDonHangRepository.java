package web.backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import web.backend.model.ChiTietDonHang;

import java.util.List;

public interface ChiTietDonHangRepository extends JpaRepository<ChiTietDonHang, Long> {
    List<ChiTietDonHang> findByDonHang_MaDH(Long maDH);
}
