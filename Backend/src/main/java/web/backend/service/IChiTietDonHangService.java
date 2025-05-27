package web.backend.service;

import web.backend.model.ChiTietDonHang;

import java.util.List;

public interface IChiTietDonHangService {
    List<ChiTietDonHang> getAll();
    ChiTietDonHang getById(Long id);
    ChiTietDonHang save(ChiTietDonHang chiTiet);
    void delete(Long id);
    List<ChiTietDonHang> findByDonHangId(Long maDH);
}
