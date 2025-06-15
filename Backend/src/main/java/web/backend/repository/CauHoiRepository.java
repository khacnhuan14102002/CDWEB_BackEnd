package web.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.backend.model.CauHoi;

import java.util.List;

public interface CauHoiRepository extends JpaRepository<CauHoi, Long> {
    List<CauHoi> findByMaSP(Long maSP);
}
