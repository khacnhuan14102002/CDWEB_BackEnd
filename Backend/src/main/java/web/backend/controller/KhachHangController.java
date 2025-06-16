    package web.backend.controller;

    import jakarta.persistence.CascadeType;
    import jakarta.persistence.OneToMany;
    import jakarta.servlet.http.HttpServletRequest;
    import jakarta.validation.Valid;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.context.MessageSource;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.servlet.LocaleResolver;
    import web.backend.jwt.JwtUtil;
    import web.backend.model.DonHang;
    import web.backend.model.KhachHang;
    import web.backend.repository.KhachHangRepository;
    import web.backend.service.EmailService;
    import web.backend.service.IKhachHangService;

    import java.time.LocalDateTime;
    import java.util.List;
    import java.util.Locale;
    import java.util.Map;
    import java.util.Optional;

    @RestController
    @RequestMapping("/api/khachhang")
    @CrossOrigin(origins = "*")
    public class KhachHangController {
        @Autowired
        private PasswordEncoder passwordEncoder;
        @Autowired
        private IKhachHangService khachHangService;

        @Autowired
        private MessageSource messageSource;

        @Autowired
        private LocaleResolver localeResolver;

        @Autowired
        private KhachHangRepository khachHangRepository;
        @Autowired
        private EmailService emailService;

        @Autowired
        private JwtUtil jwtUtil;

        @PostMapping("/login")
        public ResponseEntity<?> login(@Valid @RequestBody Map<String, String> credentials) {
            String email = credentials.get("email");
            String matKhau = credentials.get("matKhau");

            Optional<KhachHang> khachHang = khachHangService.login(email, matKhau);
            if (khachHang.isPresent()) {
                String token = jwtUtil.generateToken(khachHang.get());
                return ResponseEntity.ok(Map.of(
                        "token", token,
                        "user", khachHang.get()
                ));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Sai email hoặc mật khẩu");
            }
        }


        @PostMapping("/register")
        public ResponseEntity<String> register(@Valid @RequestBody KhachHang khachHang, HttpServletRequest request) {
            // Mã hóa mật khẩu trước khi lưu
            khachHang.setMatKhau(passwordEncoder.encode(khachHang.getMatKhau()));

            khachHangService.register(khachHang);
            return ResponseEntity.ok("Successfull");
        }


        @PostMapping("/check-email")
        public ResponseEntity<String> checkEmail(@Valid @RequestBody Map<String, String> requestBody, HttpServletRequest request) {
            String email = requestBody.get("email");
            boolean exists = khachHangService.emailExists(email);

            if (exists) {
                // Return the message key directly
                return ResponseEntity.status(HttpStatus.CONFLICT).body("email.exists");
            }
            // Return the message key directly
            return ResponseEntity.ok("email.valid");
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
        public ResponseEntity<KhachHang> createKhachHang(@Valid @RequestBody KhachHang khachHang) {
            KhachHang savedKhachHang = khachHangService.save(khachHang);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedKhachHang);
        }


        @PutMapping("/{id}")
        public ResponseEntity<KhachHang> updateKhachHang(@PathVariable Long id, @Valid @RequestBody KhachHang khachHang) {
            KhachHang existingKhachHang = khachHangService.getById(id);
            if (existingKhachHang != null) {
                khachHang.setMaKH(id);
                KhachHang updated = khachHangService.save(khachHang);
                return ResponseEntity.ok(updated);
            }
            return ResponseEntity.notFound().build();
        }

        @PostMapping("/forgot-password")
        public ResponseEntity<String> forgotPassword(@RequestBody Map<String, String> request) {
            String email = request.get("email");
            Optional<KhachHang> optional = khachHangRepository.findByEmail(email);

            if (optional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email không tồn tại!");
            }

            KhachHang kh = optional.get();

            // Tạo mã OTP 6 chữ số
            String otp = String.valueOf((int)(Math.random() * 900000) + 100000);
            kh.setOtpCode(otp);

            // Đặt thời gian hết hạn OTP là 2 phút
            kh.setOtpExpiry(LocalDateTime.now().plusMinutes(2));

            khachHangRepository.save(kh);
            emailService.sendOtp(email, otp); // Gửi email OTP

            return ResponseEntity.ok("OTP đã được gửi tới email. Có hiệu lực trong 2 phút.");
        }



        //    @DeleteMapping("/{id}")
    //    public ResponseEntity<Void> deleteKhachHang(@PathVariable Long id) {
    //        KhachHang existingKhachHang = khachHangService.getById(id);
    //        if (existingKhachHang != null) {
    //            khachHangService.delete(id);
    //            return ResponseEntity.noContent().build();
    //        } else {
    //            return ResponseEntity.notFound().build();
    //        }
    //    }
    //    @OneToMany(mappedBy = "khachHang", cascade = CascadeType.REMOVE)
    //    private List<DonHang> donHangs;
        @DeleteMapping("/{id}")
        public ResponseEntity<String> deleteKhachHang(@PathVariable Long id) {
            KhachHang existingKhachHang = khachHangService.getById(id);
            if (existingKhachHang == null) {
                return ResponseEntity.notFound().build();
            }

            // Kiểm tra có đơn hàng không
            if (khachHangService.hasDonHangByKhachHangId(id)) {
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body("Không thể xóa khách hàng vì đã có đơn hàng.");
            }

            khachHangService.delete(id);
            return ResponseEntity.noContent().build();
        }

        // API tìm kiếm
        @GetMapping("/search")
        public ResponseEntity<List<KhachHang>> searchKhachHang(@RequestParam("term") String term) {
            System.out.println("Đang tìm kiếm với từ khóa: " + term);

            // Thêm wildcard %
            String keyword = "%" + term + "%";

            List<KhachHang> result = khachHangRepository
                    .findByHoTenContainingIgnoreCaseOrEmailContainingIgnoreCaseOrSoDienThoaiContainingIgnoreCase(term, term, term);

            // In kết quả ra log
            result.forEach(kh -> System.out.println(" - " + kh.getHoTen() + " | " + kh.getEmail()));

            return ResponseEntity.ok(result);
        }
        @PostMapping("/reset-password")
        public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> request) {
            String email = request.get("email");
            String otp = request.get("otp");
            String newPassword = request.get("newPassword");

            Optional<KhachHang> optional = khachHangRepository.findByEmail(email);
            if (optional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email không tồn tại.");
            }

            KhachHang kh = optional.get();

            if (!otp.equals(kh.getOtpCode())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("OTP không chính xác.");
            }

            if (kh.getOtpExpiry() == null || kh.getOtpExpiry().isBefore(LocalDateTime.now())) {
                return ResponseEntity.status(HttpStatus.GONE).body("OTP đã hết hạn. Vui lòng thử lại.");
            }

            // Mã hóa mật khẩu trước khi lưu
            kh.setMatKhau(passwordEncoder.encode(newPassword));
            kh.setOtpCode(null);       // Xoá OTP sau khi sử dụng
            kh.setOtpExpiry(null);     // Xoá thời gian hết hạn

            khachHangRepository.save(kh);

            return ResponseEntity.ok("Mật khẩu đã được cập nhật.");
        }





    }
