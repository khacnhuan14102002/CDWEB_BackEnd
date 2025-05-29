package web.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.backend.DTO.ChiTietDonHangDTO;
import web.backend.model.ChiTietDonHang;
import web.backend.model.DonHang;
import web.backend.model.SanPham;
import web.backend.repository.ChiTietDonHangRepository;
import web.backend.repository.DonHangRepository;
import web.backend.repository.SanPhamRepository;
import web.backend.service.IChiTietDonHangService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/chitietdonhang")
public class ChiTietDonHangController {
    @Autowired
    private IChiTietDonHangService service;
    @Autowired
    private DonHangRepository donHangRepository;
    @Autowired
    private ChiTietDonHangRepository repository;
    @Autowired
    private SanPhamRepository sanPhamRepository;
    @GetMapping
    public List<ChiTietDonHang> getAll() {
        return service.getAll();
    }
    @PostMapping
    public ResponseEntity<?> createChiTietDonHang(@RequestBody ChiTietDonHangDTO dto) {
        Optional<DonHang> donHang = donHangRepository.findById(dto.getMaDH());
        Optional<SanPham> sanPham = sanPhamRepository.findById(dto.getMaSP());

        if (donHang.isEmpty() || sanPham.isEmpty()) {
            return ResponseEntity.badRequest().body("Đơn hàng hoặc sản phẩm không tồn tại");
        }

        ChiTietDonHang ct = new ChiTietDonHang();
        ct.setDonHang(donHang.get());
        ct.setSanPham(sanPham.get());
        ct.setSoLuong(dto.getSoLuong());
        ct.setDonGia(dto.getDonGia());
        ct.setThanhTien(dto.getThanhTien());
        ct.setKichThuoc(dto.getKichThuoc());

        repository.save(ct);

        return ResponseEntity.ok("Chi tiết đơn hàng đã được lưu");
    }

    @GetMapping("/{id}")
    public ChiTietDonHang getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/donhang/{maDH}")
    public List<ChiTietDonHang> getByDonHangId(@PathVariable Long maDH) {
        return service.findByDonHangId(maDH);
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
    @GetMapping("/bestsellers")
    public List<SanPham> getTop5BestSellers() {
        return service.findTop5BestSellingProducts();
    }

}
