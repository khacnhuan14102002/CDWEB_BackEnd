package web.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.backend.model.ChiTietHoaDon;
import web.backend.model.ChiTietHoaDonId;
import web.backend.repository.ChiTietHoaDonRepository;

import java.util.List;

@Service
public class ChiTietHoaDonServiceImpl implements IChiTietHoaDonService {

    @Autowired
    private ChiTietHoaDonRepository repository;

    @Override
    public List<ChiTietHoaDon> getAll() {
        return repository.findAll();
    }

    @Override
    public ChiTietHoaDon getById(ChiTietHoaDonId id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public ChiTietHoaDon save(ChiTietHoaDon cthd) {
        return repository.save(cthd);
    }

    @Override
    public void delete(ChiTietHoaDonId id) {
        repository.deleteById(id);
    }
}
