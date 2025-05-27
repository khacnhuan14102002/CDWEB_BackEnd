package web.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.backend.model.SanPham;

import java.util.List;

public interface SanPhamRepository extends JpaRepository<SanPham, Long> { ;

    List<SanPham> findByType_maType(Long maType);

    List<SanPham> findByDanhMuc_MaDanhMuc(Long maDanhMuc);

    List<SanPham> findByType_maTypeIn(List<Long> typeIds);

    List<SanPham> findByDanhMuc_MaDanhMucAndType_maTypeIn(Long danhMucId, List<Long> typeIds);
}