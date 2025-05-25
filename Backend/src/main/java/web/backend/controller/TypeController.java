package web.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.backend.model.DanhMuc;
import web.backend.model.Type;
import web.backend.service.IDanhMucService;
import web.backend.service.ITypeService;

import java.util.List;
@RestController
@RequestMapping("/api/type")
public class TypeController
{
    @Autowired
    private ITypeService danhMucService;

    @GetMapping
    public List<Type> getAll() {
        return danhMucService.getAll();
    }

    @GetMapping("/{id}")
    public Type getById(@PathVariable Long id) {
        return danhMucService.getById(id);
    }

    @PostMapping
    public Type create(@RequestBody Type danhMuc) {
        return danhMucService.save(danhMuc);
    }

    @PutMapping("/{id}")
    public Type update(@PathVariable Long id, @RequestBody Type danhMuc) {
        danhMuc.setMaType(id);
        return danhMucService.save(danhMuc);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        danhMucService.delete(id);
    }

}
