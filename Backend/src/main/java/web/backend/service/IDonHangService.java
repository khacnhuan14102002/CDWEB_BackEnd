package web.backend.service;

import web.backend.model.DonHang;
import java.util.List;

public interface IDonHangService {
    List<DonHang> getAll();
    DonHang getById(Long id);
    DonHang save(DonHang donHang);
    void delete(Long id);

    List<DonHang> getByKhachHangId(Long maKH);
}
