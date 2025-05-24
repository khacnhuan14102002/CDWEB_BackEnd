package web.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.backend.model.SanPham;
import web.backend.service.ISanPhamService;

import java.util.List;

@RestController
@RequestMapping("/api/sanpham")
public class SanPhamController {

    @Autowired
    private ISanPhamService sanPhamService;

    @GetMapping
    public List<SanPham> getAll() {
        return sanPhamService.getAll();
    }

    @GetMapping("/{id}")
    public SanPham getById(@PathVariable Long id) {
        return sanPhamService.getById(id);
    }

    @PostMapping
    public SanPham create(@RequestBody SanPham sanPham) {
        return sanPhamService.save(sanPham);
    }

    @PutMapping("/{id}")
    public SanPham update(@PathVariable Long id, @RequestBody SanPham sanPham) {
        sanPham.setMaSP(id);
        return sanPhamService.save(sanPham);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        sanPhamService.delete(id);
    }
}
