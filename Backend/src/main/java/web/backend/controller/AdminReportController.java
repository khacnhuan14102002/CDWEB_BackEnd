package web.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.backend.repository.DonHangRepository;
import web.backend.service.IAdminReportService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/report")
@CrossOrigin(origins = "*")
public class AdminReportController {

    @Autowired
    private IAdminReportService adminReportService;
    @Autowired
    private DonHangRepository donHangRepository;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getReport() {
        Map<String, Object> result = new HashMap<>();

        // Tổng doanh thu
        Double tongDoanhThu = adminReportService.getTotalRevenue();
        result.put("tongDoanhThu", tongDoanhThu != null ? tongDoanhThu : 0);

        // Số lượng đơn hàng
        Long soLuongDonHang = adminReportService.getTotalOrders();
        result.put("soLuongDonHang", soLuongDonHang);

        // Sản phẩm bán chạy
        List<Map<String, Object>> topProducts = adminReportService.getTopSellingProducts();
        result.put("sanPhamBanChay", topProducts);

        return ResponseEntity.ok(result);
    }
    @GetMapping("/doanh-thu/ngay")
    public ResponseEntity<List<Map<String, Object>>> getDoanhThuNgay() {
        List<Map<String, Object>> result = donHangRepository.doanhThuTheoNgay();
        return ResponseEntity.ok(result);
    }
}
