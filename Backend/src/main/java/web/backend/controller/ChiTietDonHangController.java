package web.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.backend.model.ChiTietDonHang;
import web.backend.service.IChiTietDonHangService;

import java.util.List;

@RestController
@RequestMapping("/api/chitietdonhang")
public class ChiTietDonHangController {
    @Autowired
    private IChiTietDonHangService service;

    @GetMapping
    public List<ChiTietDonHang> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ChiTietDonHang getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/donhang/{maDH}")
    public List<ChiTietDonHang> getByDonHangId(@PathVariable Long maDH) {
        return service.findByDonHangId(maDH);
    }

    @PostMapping
    public ChiTietDonHang create(@RequestBody ChiTietDonHang chiTiet) {
        return service.save(chiTiet);
    }

    @PutMapping("/{id}")
    public ChiTietDonHang update(@PathVariable Long id, @RequestBody ChiTietDonHang chiTiet) {
        chiTiet.setMaCT(id);
        return service.save(chiTiet);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
