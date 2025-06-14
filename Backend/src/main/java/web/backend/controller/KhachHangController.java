    package web.backend.controller;

    import jakarta.persistence.CascadeType;
    import jakarta.persistence.OneToMany;
    import jakarta.servlet.http.HttpServletRequest;
    import jakarta.validation.Valid;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.context.MessageSource;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.servlet.LocaleResolver;
    import web.backend.model.DonHang;
    import web.backend.model.KhachHang;
    import web.backend.repository.KhachHangRepository;
    import web.backend.service.IKhachHangService;

    import java.util.List;
    import java.util.Locale;
    import java.util.Map;
    import java.util.Optional;

    @RestController
    @RequestMapping("/api/khachhang")
    @CrossOrigin(origins = "*")
    public class KhachHangController {

        @Autowired
        private IKhachHangService khachHangService;

        @Autowired
        private MessageSource messageSource;

        @Autowired
        private LocaleResolver localeResolver;

        @Autowired
        private KhachHangRepository khachHangRepository;

        @PostMapping("/login")
        public ResponseEntity<?> login(@Valid @RequestBody Map<String, String> credentials, HttpServletRequest request) {
            String email = credentials.get("email");
            String matKhau = credentials.get("matKhau");
            Locale locale = localeResolver.resolveLocale(request);

            Optional<KhachHang> khachHang = khachHangService.login(email, matKhau);
            if (khachHang.isPresent()) {
                return ResponseEntity.ok(khachHang.get());
            } else {
                String message = messageSource.getMessage("login.invalid", null, locale);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
            }
        }

        @PostMapping("/register")
        public ResponseEntity<String> register(@Valid @RequestBody KhachHang khachHang, HttpServletRequest request) {
            Locale locale = localeResolver.resolveLocale(request);
            String message = khachHangService.register(khachHang);
            if ("success".equals(message)) {
                return ResponseEntity.ok(messageSource.getMessage("register.success", null, locale));
            }
            // Assuming 'message' here is already a key from your service if an error occurs
            // If it's a raw string, you might want to return a generic error key or map it.
            // For now, let's assume it's a key or a raw error from the service.
            return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
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




    }
