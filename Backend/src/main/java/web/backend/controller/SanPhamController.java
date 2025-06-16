package web.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.backend.DTO.SanPhamChiTietDTO;
import web.backend.DTO.SanPhamDTO;
import web.backend.model.SanPham;
import web.backend.model.SanPhamChiTiet;
import web.backend.service.ISanPhamService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/sanpham")
public class SanPhamController {
    @Autowired
    private ISanPhamService sanPhamService;

    @GetMapping("/danhmuc/{id}")
    public List<SanPham> getByDanhMuc(@PathVariable Long id) {
        return sanPhamService.getByDanhMucId(id);
    }

    @GetMapping("/type/{typeId}")
    public List<SanPhamDTO> getByType(@PathVariable Long typeId) {
        List<SanPham> sanPhams = sanPhamService.getByTypeId(typeId);
        return sanPhams.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @GetMapping("/filter")
    public List<SanPhamDTO> filterByDanhMucAndType(
            @RequestParam(required = false) Long danhmuc,
            @RequestParam(required = false) List<Long> types) {
        List<SanPham> sanPhams = sanPhamService.filterByDanhMucAndTypes(danhmuc, types);
        return sanPhams.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @GetMapping
    public List<SanPhamDTO> getAll() {
        List<SanPham> sanPhams = sanPhamService.getAll();
        return sanPhams.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public SanPhamDTO getById(@PathVariable Long id) {
        SanPham sanPham = sanPhamService.getById(id);
        return convertToDTO(sanPham);
    }

    @PostMapping
    public SanPham create(@RequestBody SanPham sanPham) {
        // Khi tạo mới, maSP của sản phẩm cha sẽ được gán tự động sau khi save
        // Logic thiết lập mối quan hệ hai chiều sẽ nằm trong service
        return sanPhamService.save(sanPham);
    }

    @PutMapping("/{id}")
    public SanPham update(@PathVariable Long id, @RequestBody SanPham sanPham) {
        // Đảm bảo maSP của sản phẩm cha được thiết lập từ URL path
        sanPham.setMaSP(id);
        // Logic thiết lập mối quan hệ hai chiều và quản lý chi tiết sẽ nằm trong service
        return sanPhamService.save(sanPham);
    }

    @DeleteMapping("/{id}")

    public void delete(@PathVariable Long id) {
        sanPhamService.delete(id);
    }

    // Helper method to convert SanPham entity to SanPhamDTO
    private SanPhamDTO convertToDTO(SanPham sanPham) {
        SanPhamDTO dto = new SanPhamDTO();
        dto.setMaSP(sanPham.getMaSP());
        dto.setTenSP(sanPham.getTenSP());
        dto.setMoTa(sanPham.getMoTa());
        dto.setHinhAnh(sanPham.getHinhAnh());

        if (sanPham.getDanhMuc() != null) {
            dto.setMaDanhMuc(sanPham.getDanhMuc().getMaDanhMuc());
            dto.setTenDanhMuc(sanPham.getDanhMuc().getTenDanhMuc());
        } else {
            dto.setMaDanhMuc(null);
            dto.setTenDanhMuc("Không xác định");
        }

        if (sanPham.getType() != null) {
            dto.setMaType(sanPham.getType().getMaType());
            dto.setTenType(sanPham.getType().getTenType());
        } else {
            dto.setMaType(null);
            dto.setTenType("Không xác định");
        }

        if (sanPham.getChiTietList() != null) {
            dto.setChiTietList(sanPham.getChiTietList().stream()
                    .map(chiTiet -> convertToChiTietDTO(chiTiet, sanPham.getMaSP()))
                    .collect(Collectors.toList()));
        } else {
            dto.setChiTietList(new ArrayList<>());
        }
        return dto;
    }


    private SanPhamChiTietDTO convertToChiTietDTO(SanPhamChiTiet chiTiet, Long sanPhamMaSP) {
        SanPhamChiTietDTO dto = new SanPhamChiTietDTO();
        dto.setId(chiTiet.getId());
        dto.setKichCo(chiTiet.getKichCo());
        dto.setGia(chiTiet.getGia());
        dto.setSoLuong(chiTiet.getSoLuong());
        if (sanPhamMaSP != null) {
            dto.setMaSP(sanPhamMaSP.toString());
        } else if (chiTiet.getSanPham() != null && chiTiet.getSanPham().getMaSP() != null) {
            dto.setMaSP(chiTiet.getSanPham().getMaSP().toString());
        } else {
            dto.setMaSP(null);
        }
        return dto;
    }

    @GetMapping("/oldest")
    public List<SanPhamDTO> getOldestProducts() {
        List<SanPham> oldest = sanPhamService.getOldestProducts(10);
        return oldest.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @GetMapping("/search")
    public List<SanPhamDTO> searchByKeyword(@RequestParam String keyword) {
        List<SanPham> results = sanPhamService.searchByKeyword(keyword);
        return results.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    @GetMapping("/related")
    public List<SanPhamDTO> getRelatedProducts(
            @RequestParam Long danhmuc,
            @RequestParam Long type,
            @RequestParam(required = false) Long excludeId) {

        List<SanPham> relatedProducts = sanPhamService.filterByDanhMucAndType(danhmuc, List.of(type));

        // Loại bỏ sản phẩm đang xem (nếu truyền vào)
        if (excludeId != null) {
            relatedProducts = relatedProducts.stream()
                    .filter(sp -> !sp.getMaSP().equals(excludeId))
                    .limit(5)
                    .collect(Collectors.toList());
        } else {
            relatedProducts = relatedProducts.stream().limit(5).collect(Collectors.toList());
        }

        return relatedProducts.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

}
