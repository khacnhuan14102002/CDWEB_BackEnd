package web.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.backend.model.DonHang;
import web.backend.repository.DonHangRepository;

import java.util.List;

@Service
public class DonHangServiceImpl implements IDonHangService {

    @Autowired
    private DonHangRepository donHangRepository;

    @Override
    public List<DonHang> getAll() {
        return donHangRepository.findAll();
    }

    @Override
    public DonHang getById(Long id) {
        return donHangRepository.findById(id).orElse(null);
    }

    @Override
    public DonHang save(DonHang donHang) {
        return donHangRepository.save(donHang);
    }

    @Override
    public void delete(Long id) {
        donHangRepository.deleteById(id);
    }

}
