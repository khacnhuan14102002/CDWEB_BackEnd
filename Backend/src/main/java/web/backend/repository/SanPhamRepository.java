package web.backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import web.backend.model.*;

import java.util.List;


public interface SanPhamRepository extends JpaRepository<SanPham, Long> {
    List<SanPham> findByDanhMuc_MaDanhMuc(Long maDanhMuc);
    List<SanPham> findByTypeMaType(Long maType);

}