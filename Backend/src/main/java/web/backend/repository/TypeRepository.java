package web.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.backend.model.DanhMuc;
import web.backend.model.Type;

public interface TypeRepository extends JpaRepository<Type, Long> {}