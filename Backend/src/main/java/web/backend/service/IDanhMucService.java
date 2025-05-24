package web.backend.service;

import web.backend.model.DanhMuc;
import java.util.List;

public interface IDanhMucService {
    List<DanhMuc> getAll();
    DanhMuc getById(Long id);
    DanhMuc save(DanhMuc danhMuc);
    void delete(Long id);
}
