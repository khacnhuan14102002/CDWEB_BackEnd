package web.backend.service;

import web.backend.model.DanhMuc;
import web.backend.model.Type;

import java.util.List;

public interface ITypeService {
    List<Type> getAll();
    Type getById(Long id);
    Type save(Type type);
    void delete(Long id);
}
