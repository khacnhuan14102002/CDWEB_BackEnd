package web.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.backend.model.SanPham;
import web.backend.repository.SanPhamRepository;

import java.util.List;

@Service
public class SanPhamServiceImpl implements ISanPhamService {
    @Autowired
    private SanPhamRepository sanPhamRepository;

    @Override
    public List<SanPham> getAll() {
        return sanPhamRepository.findAll();
    }

    @Override
    public SanPham getById(Long id) {
        return sanPhamRepository.findById(id).orElse(null);
    }

    @Override
    public SanPham save(SanPham sanPham) {
        return sanPhamRepository.save(sanPham);
    }

    @Override
    public void delete(Long id) {
        sanPhamRepository.deleteById(id);
    }

    @Override
    public List<SanPham> getByDanhMucId(Long maDanhMuc) {
        // Calls the corrected repository method
        return sanPhamRepository.findByDanhMuc_MaDanhMuc(maDanhMuc);
    }

    @Override
    public List<SanPham> getByTypeId(Long typeId) {
        return sanPhamRepository.findByType_maType(typeId);
    }

    @Override
    public List<SanPham> filterByDanhMucAndTypes(Long danhMucId, List<Long> typeIds) {
        if (danhMucId != null && typeIds != null && !typeIds.isEmpty()) {
            // Calls the corrected repository method
            return sanPhamRepository.findByDanhMuc_MaDanhMucAndType_maTypeIn(danhMucId, typeIds);
        } else if (danhMucId != null) {
            // Calls the corrected repository method
            return sanPhamRepository.findByDanhMuc_MaDanhMuc(danhMucId);
        } else if (typeIds != null && !typeIds.isEmpty()) {
            return sanPhamRepository.findByType_maTypeIn(typeIds);
        } else {
            return sanPhamRepository.findAll();
        }
    }
    @Override
    public List<SanPham> getOldestProducts(int limit) {
        return sanPhamRepository.findOldestProducts(limit);
    }

}