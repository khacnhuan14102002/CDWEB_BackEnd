package web.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.backend.model.DonHang;
import web.backend.service.IDonHangService;

import java.util.List;

@RestController
@RequestMapping("/api/donhang")
public class DonHangController {

    @Autowired
    private IDonHangService donHangService;

    @GetMapping
    public List<DonHang> getAll() {
        return donHangService.getAll();
    }

    @GetMapping("/{id}")
    public DonHang getById(@PathVariable Long id) {
        return donHangService.getById(id);
    }

    @PostMapping
    public DonHang create(@RequestBody DonHang donHang) {
        return donHangService.save(donHang);
    }

    @PutMapping("/{id}")
    public DonHang update(@PathVariable Long id, @RequestBody DonHang donHang) {
        donHang.setMaDH(id);
        return donHangService.save(donHang);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        donHangService.delete(id);
    }
}
