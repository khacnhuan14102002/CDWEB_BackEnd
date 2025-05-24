package web.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.backend.model.ChiTietHoaDon;
import web.backend.model.ChiTietHoaDonId;
import web.backend.service.IChiTietHoaDonService;

import java.util.List;

@RestController
@RequestMapping("/api/cthd")
public class ChiTietHoaDonController {

    @Autowired
    private IChiTietHoaDonService service;

    @GetMapping
    public List<ChiTietHoaDon> getAll() {
        return service.getAll();
    }

    @GetMapping("/{maDH}/{maSP}")
    public ChiTietHoaDon getById(@PathVariable Long maDH, @PathVariable Long maSP) {
        return service.getById(new ChiTietHoaDonId(maDH, maSP));
    }

    @PostMapping
    public ChiTietHoaDon create(@RequestBody ChiTietHoaDon cthd) {
        return service.save(cthd);
    }

    @PutMapping("/{maDH}/{maSP}")
    public ChiTietHoaDon update(@PathVariable Long maDH, @PathVariable Long maSP,
                                @RequestBody ChiTietHoaDon cthd) {
        cthd.setDonHang(cthd.getDonHang());
        cthd.setSanPham(cthd.getSanPham());
        return service.save(cthd);
    }

    @DeleteMapping("/{maDH}/{maSP}")
    public void delete(@PathVariable Long maDH, @PathVariable Long maSP) {
        service.delete(new ChiTietHoaDonId(maDH, maSP));
    }
}
