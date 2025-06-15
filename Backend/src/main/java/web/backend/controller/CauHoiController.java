package web.backend.controller;

import org.springframework.web.bind.annotation.*;
import web.backend.model.CauHoi;
import web.backend.service.CauHoiService;

import java.util.List;

@RestController
@RequestMapping("/api/cauhoi")
public class CauHoiController {

    private final CauHoiService service;

    public CauHoiController(CauHoiService service) {
        this.service = service;
    }

    // ✅ Lấy tất cả câu hỏi
    @GetMapping
    public List<CauHoi> getAll() {
        return service.getAll();
    }

    // ✅ Lấy câu hỏi theo mã sản phẩm (nếu cần)
    @GetMapping("/sanpham/{maSP}")
    public List<CauHoi> getBySanPham(@PathVariable Long maSP) {
        return service.getByMaSP(maSP);
    }

    @PostMapping("/them")
    public CauHoi themCauHoi(@RequestBody CauHoi cauHoi) {
        return service.save(cauHoi);
    }

    @PostMapping("/traloi/{id}")
    public CauHoi traLoi(@PathVariable Long id, @RequestBody String traLoi) {
        return service.traLoi(id, traLoi);
    }
}
