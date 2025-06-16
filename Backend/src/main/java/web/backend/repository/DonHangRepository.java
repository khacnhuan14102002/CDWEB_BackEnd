package web.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import web.backend.model.*;

import java.util.List;
import java.util.Map;

public interface DonHangRepository extends JpaRepository<DonHang, Long> {
    List<DonHang> findByKhachHang_MaKH(Long maKH);
    boolean existsByKhachHang_MaKH(Long maKH);

    @Query("SELECT d FROM DonHang d JOIN FETCH d.khachHang kh LEFT JOIN FETCH d.chiTietDonHang ct LEFT JOIN FETCH ct.sanPham")
    List<DonHang> findAllWithChiTietAndKhachHang();

    @Query("SELECT d FROM DonHang d JOIN FETCH d.khachHang kh LEFT JOIN FETCH d.chiTietDonHang ct LEFT JOIN FETCH ct.sanPham WHERE kh.hoTen LIKE %:name%")
    List<DonHang> findByKhachHangTenContaining(@Param("name") String name);
// thong kê tính tổng danh thu
    @Query("SELECT SUM(d.tongTien) FROM DonHang d WHERE d.trangThai = 'Đã giao'")
    Double tinhTongDoanhThu();

    @Query("SELECT COUNT(d) FROM DonHang d")
    Long demSoDonHang();
// danh thu theo ngày
    @Query("SELECT new map(FUNCTION('DATE', d.ngayDat) as ngay, SUM(d.tongTien) as tongTien) " +
            "FROM DonHang d WHERE d.trangThai = 'Đã giao' " +
            "GROUP BY FUNCTION('DATE', d.ngayDat) ORDER BY ngay")
    List<Map<String, Object>> doanhThuTheoNgay();



}