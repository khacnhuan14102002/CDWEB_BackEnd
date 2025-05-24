package web.backend.service;

import web.backend.model.SanPham;
import java.util.List;

public interface ISanPhamService {
    List<SanPham> getAll();
    SanPham getById(Long id);
    SanPham save(SanPham sanPham);
    void delete(Long id);
}
