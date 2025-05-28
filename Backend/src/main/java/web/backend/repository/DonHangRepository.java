package web.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.backend.model.*;

import java.util.List;

public interface DonHangRepository extends JpaRepository<DonHang, Long> {
    List<DonHang> findByKhachHang_MaKH(Long maKH);
}