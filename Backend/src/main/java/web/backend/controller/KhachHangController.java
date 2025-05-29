package web.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.backend.model.KhachHang;
import web.backend.service.IKhachHangService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/khachhang")
@CrossOrigin(origins = "*")
public class KhachHangController {

    @Autowired
    private IKhachHangService khachHangService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String matKhau = credentials.get("matKhau");

        Optional<KhachHang> khachHang = khachHangService.login(email, matKhau);
        if (khachHang.isPresent()) {
            return ResponseEntity.ok(khachHang.get());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Sai email hoặc mật khẩu!");
        }
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody KhachHang khachHang) {
        String message = khachHangService.register(khachHang);
        return ResponseEntity.ok(message);
    }
    @PostMapping("/check-email")
    public ResponseEntity<String> checkEmail(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        boolean exists = khachHangService.emailExists(email);
        return exists ? ResponseEntity.status(HttpStatus.CONFLICT).body("Email đã tồn tại!") : ResponseEntity.ok("Email hợp lệ.");
    }
    @GetMapping
    public ResponseEntity<List<KhachHang>> getAllKhachHang() {
        List<KhachHang> khachHangs = khachHangService.getAll();
        return ResponseEntity.ok(khachHangs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<KhachHang> getKhachHangById(@PathVariable Long id) {
        KhachHang khachHang = khachHangService.getById(id);
        if (khachHang != null) {
            return ResponseEntity.ok(khachHang);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<KhachHang> createKhachHang(@RequestBody KhachHang khachHang) {

        KhachHang savedKhachHang = khachHangService.save(khachHang);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedKhachHang);
    }

    @PutMapping("/{id}")
    public ResponseEntity<KhachHang> udateKhachHang(@PathVariable Long id, @RequestBody KhachHang khachHang) {
        KhachHang existingKhachHang = khachHangService.getById(id);
        if (existingKhachHang != null) {
            khachHang.setMaKH(id);
            KhachHang updatedKhachHang = khachHangService.save(khachHang);
            return ResponseEntity.ok(updatedKhachHang);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKhachHang(@PathVariable Long id) {
        KhachHang existingKhachHang = khachHangService.getById(id);
        if (existingKhachHang != null) {
            khachHangService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
