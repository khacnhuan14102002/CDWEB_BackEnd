package web.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.backend.model.DanhMuc;
import web.backend.service.IDanhMucService;

import java.util.List;

@RestController
@RequestMapping("/api/danhmuc")
public class DanhMucController {

    @Autowired
    private IDanhMucService danhMucService;

    @GetMapping
    public List<DanhMuc> getAll() {
        return danhMucService.getAll();
    }

    @GetMapping("/{id}")
    public DanhMuc getById(@PathVariable Long id) {
        return danhMucService.getById(id);
    }

    @PostMapping
    public DanhMuc create(@RequestBody DanhMuc danhMuc) {
        return danhMucService.save(danhMuc);
    }

    @PutMapping("/{id}")
    public DanhMuc update(@PathVariable Long id, @RequestBody DanhMuc danhMuc) {
        danhMuc.setMaDanhMuc(id);
        return danhMucService.save(danhMuc);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        danhMucService.delete(id);
    }
}
