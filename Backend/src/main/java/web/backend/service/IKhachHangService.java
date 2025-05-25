package web.backend.service;

import web.backend.model.KhachHang;
import java.util.List;
import java.util.Optional;

public interface IKhachHangService {
    List<KhachHang> getAll();
    KhachHang getById(Long id);
    KhachHang save(KhachHang khachHang);
    void delete(Long id);
    Optional<KhachHang> login(String email, String matKhau);
    String register(KhachHang khachHang);

    boolean emailExists(String email);
}
