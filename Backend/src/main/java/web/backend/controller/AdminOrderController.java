package web.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.backend.model.DonHang;
import web.backend.repository.DonHangRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/orders")
@CrossOrigin(origins = "*")
public class AdminOrderController {

    @Autowired
    private DonHangRepository donHangRepository;

    @GetMapping
    public ResponseEntity<List<DonHang>> getAllOrders() {
        return ResponseEntity.ok(donHangRepository.findAllWithChiTietAndKhachHang());
    }

    @GetMapping("/search")
    public ResponseEntity<List<DonHang>> searchByCustomer(@RequestParam("term") String term) {
        return ResponseEntity.ok(donHangRepository.findByKhachHangTenContaining(term));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<String> updateOrderStatus(@PathVariable Long id, @RequestBody Map<String, String> body) {
        Optional<DonHang> optional = donHangRepository.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        String newStatus = body.get("trangThai");
        DonHang order = optional.get();
        order.setTrangThai(newStatus);
        donHangRepository.save(order);

        return ResponseEntity.ok("Trạng thái đã được cập nhật.");
    }

}
