package web.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.backend.model.KhachHang;
import web.backend.service.IKhachHangService;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/khachhang")
@CrossOrigin(origins = "*") // Cho phép gọi từ frontend React
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
}
