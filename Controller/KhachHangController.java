import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/khachhang")
public class KhachHangController {
    private final KhachHangService khachHangService;

    public KhachHangController(KhachHangService khachHangService) {
        this.khachHangService = khachHangService;
    }

    @GetMapping
    public List<KhachHang> getAllKhachHang() {
        return khachHangService.getAllKhachHang();
    }

    @GetMapping("/{id}")
    public Optional<KhachHang> getKhachHangById(@PathVariable Long id) {
        return khachHangService.getKhachHangById(id);
    }

    @PostMapping
    public KhachHang saveKhachHang(@RequestBody KhachHang khachHang) {
        return khachHangService.saveKhachHang(khachHang);
    }
}