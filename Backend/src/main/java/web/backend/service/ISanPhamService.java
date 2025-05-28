package web.backend.service;

import web.backend.model.SanPham;
import java.util.List;

public interface ISanPhamService {
    List<SanPham> getAll();
    SanPham getById(Long id);
    SanPham save(SanPham sanPham);
    void delete(Long id);
    List<SanPham> getByDanhMucId(Long maDanhMuc);
    List<SanPham> getByTypeId(Long typeId);
    List<SanPham> filterByDanhMucAndTypes(Long danhmuc, List<Long> types);

    List<SanPham> getOldestProducts(int limit);

    List<SanPham> searchByKeyword(String keyword);
}
