package web.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import web.backend.model.*;

import java.util.List;

public interface DonHangRepository extends JpaRepository<DonHang, Long> {
    List<DonHang> findByKhachHang_MaKH(Long maKH);
    boolean existsByKhachHang_MaKH(Long maKH);

    @Query("SELECT d FROM DonHang d JOIN FETCH d.khachHang kh LEFT JOIN FETCH d.chiTietDonHang ct LEFT JOIN FETCH ct.sanPham")
    List<DonHang> findAllWithChiTietAndKhachHang();

    @Query("SELECT d FROM DonHang d JOIN FETCH d.khachHang kh LEFT JOIN FETCH d.chiTietDonHang ct LEFT JOIN FETCH ct.sanPham WHERE kh.hoTen LIKE %:name%")
    List<DonHang> findByKhachHangTenContaining(@Param("name") String name);

}