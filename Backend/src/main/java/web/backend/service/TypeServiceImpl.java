package web.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.backend.model.DanhMuc;
import web.backend.model.Type;
import web.backend.repository.TypeRepository;

import java.util.List;

@Service
public class TypeServiceImpl implements ITypeService {

    @Autowired
    private TypeRepository typeRepository;


    @Override
    public List<Type> getAll() {
        return typeRepository.findAll();
    }

    @Override
    public Type getById(Long id) {
        return typeRepository.findById(id).orElse(null);
    }

    @Override
    public Type save(Type type) {
        return typeRepository.save(type);
    }

    @Override
    public void delete(Long id) {
        typeRepository.deleteById(id);
    }
}
