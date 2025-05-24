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
}
