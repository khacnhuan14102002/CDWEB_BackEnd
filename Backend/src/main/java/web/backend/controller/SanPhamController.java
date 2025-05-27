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
        return sanPhamService.save(sanPham);
    }

    @PutMapping("/{id}")
    public SanPham update(@PathVariable Long id, @RequestBody SanPham sanPham) {
        sanPham.setMaSP(id);
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
        // Ensure chiTietList is not null before streaming
        if (sanPham.getChiTietList() != null) {
            dto.setChiTietList(sanPham.getChiTietList().stream()
                    .map(this::convertToChiTietDTO)
                    .collect(Collectors.toList()));
        } else {
            dto.setChiTietList(new ArrayList<>()); // Initialize with an empty list
        }
        return dto;
    }

    // Helper method to convert SanPhamChiTiet entity to SanPhamChiTietDTO
    private SanPhamChiTietDTO convertToChiTietDTO(SanPhamChiTiet chiTiet) {
        SanPhamChiTietDTO dto = new SanPhamChiTietDTO();
        dto.setId(chiTiet.getId());
        dto.setKichCo(chiTiet.getKichCo());
        dto.setGia(chiTiet.getGia());
        dto.setSoLuong(chiTiet.getSoLuong());
        return dto;
    }
}