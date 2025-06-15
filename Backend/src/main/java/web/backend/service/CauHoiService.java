package web.backend.service;

import org.springframework.stereotype.Service;
import web.backend.model.CauHoi;
import web.backend.repository.CauHoiRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CauHoiService {

    private final CauHoiRepository repository;

    public CauHoiService(CauHoiRepository repository) {
        this.repository = repository;
    }

    public List<CauHoi> getAll() {
        return repository.findAll();
    }

    public List<CauHoi> getByMaSP(Long maSP) {
        return repository.findByMaSP(maSP);
    }

    public CauHoi save(CauHoi cauHoi) {
        return repository.save(cauHoi);
    }

    public CauHoi traLoi(Long id, String traLoi) {
        Optional<CauHoi> optional = repository.findById(id);
        if (optional.isPresent()) {
            CauHoi ch = optional.get();
            ch.setTraLoi(traLoi);
            return repository.save(ch);
        }
        return null;
    }
}
