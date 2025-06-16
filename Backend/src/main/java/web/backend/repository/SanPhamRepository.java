package web.backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import web.backend.model.SanPham;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SanPhamRepository extends JpaRepository<SanPham, Long> { ;

    List<SanPham> findByType_maType(Long maType);

    List<SanPham> findByDanhMuc_MaDanhMuc(Long maDanhMuc);

    List<SanPham> findByType_maTypeIn(List<Long> typeIds);

    List<SanPham> findByDanhMuc_MaDanhMucAndType_maTypeIn(Long danhMucId, List<Long> typeIds);
    @Query("SELECT s FROM SanPham s ORDER BY s.maSP ASC")
    List<SanPham> findOldestProducts(Pageable pageable);

//    default List<SanPham> findOldestProducts(int limit) {
//        return findOldestProducts((Pageable) PageRequest.of(0, limit));
//    }
default List<SanPham> findOldestProducts(int limit) {
    return findOldestProducts(PageRequest.of(0, limit));
}


    @Query("SELECT s FROM SanPham s WHERE LOWER(s.tenSP) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<SanPham> searchByKeyword(String keyword);

    @Query("SELECT s FROM SanPham s WHERE s.danhMuc.maDanhMuc = :danhmucId AND s.type.maType IN :typeIds")
    List<SanPham> findByDanhMucIdAndTypeIds(@Param("danhmucId") Long danhmucId, @Param("typeIds") List<Long> typeIds);

    List<SanPham> findByDanhMuc_MaDanhMucAndType_MaTypeAndMaSPNot(Long maDanhMuc, Long maType, Long maSP);



}