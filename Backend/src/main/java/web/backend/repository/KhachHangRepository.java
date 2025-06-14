package web.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.backend.model.*;
import java.util.List;
import java.util.Optional;

public interface KhachHangRepository extends JpaRepository<KhachHang, Long> {
    Optional<KhachHang> findByEmailAndMatKhau(String email, String matKhau);

    Optional<KhachHang> findByEmail(String email);

    // Thêm mới để hỗ trợ tìm kiếm
    List<KhachHang> findByHoTenContainingIgnoreCaseOrEmailContainingIgnoreCaseOrSoDienThoaiContainingIgnoreCase(
            String hoTen, String email, String soDienThoai);

}