package web.backend.service;

import web.backend.model.KhachHang;
import java.util.List;

public interface IKhachHangService {
    List<KhachHang> getAll();
    KhachHang getById(Long id);
    KhachHang save(KhachHang khachHang);
    void delete(Long id);
}
