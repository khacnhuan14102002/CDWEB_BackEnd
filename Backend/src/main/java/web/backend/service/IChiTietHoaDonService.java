package web.backend.service;

import web.backend.model.ChiTietHoaDon;
import web.backend.model.ChiTietHoaDonId;

import java.util.List;

public interface IChiTietHoaDonService {
    List<ChiTietHoaDon> getAll();
    ChiTietHoaDon getById(ChiTietHoaDonId id);
    ChiTietHoaDon save(ChiTietHoaDon cthd);
    void delete(ChiTietHoaDonId id);
}
