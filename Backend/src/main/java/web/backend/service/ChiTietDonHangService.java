package web.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.backend.repository.ChiTietDonHangRepository;
import web.backend.model.*;

import java.util.List;

@Service
public class ChiTietDonHangService implements IChiTietDonHangService {

    @Autowired
    private ChiTietDonHangRepository repository;

    @Override
    public List<ChiTietDonHang> getAll() {
        return repository.findAll();
    }

    @Override
    public ChiTietDonHang getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public ChiTietDonHang save(ChiTietDonHang chiTiet) {
        return repository.save(chiTiet);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<ChiTietDonHang> findByDonHangId(Long maDH) {
        return repository.findByDonHang_MaDH(maDH);
    }
}
