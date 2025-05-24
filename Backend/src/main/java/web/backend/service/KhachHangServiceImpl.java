package web.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.backend.model.KhachHang;
import web.backend.repository.KhachHangRepository;

import java.util.List;

@Service
public class KhachHangServiceImpl implements IKhachHangService {

    @Autowired
    private KhachHangRepository khachHangRepository;

    @Override
    public List<KhachHang> getAll() {
        return khachHangRepository.findAll();
    }

    @Override
    public KhachHang getById(Long id) {
        return khachHangRepository.findById(id).orElse(null);
    }

    @Override
    public KhachHang save(KhachHang khachHang) {
        return khachHangRepository.save(khachHang);
    }

    @Override
    public void delete(Long id) {
        khachHangRepository.deleteById(id);
    }
}
