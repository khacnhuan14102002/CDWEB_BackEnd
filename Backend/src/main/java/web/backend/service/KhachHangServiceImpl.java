package web.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.backend.model.KhachHang;
import web.backend.repository.KhachHangRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class KhachHangServiceImpl implements IKhachHangService {

    @Autowired
    private KhachHangRepository khachHangRepository;

    @Override
    public List<KhachHang> getAll() {
        return khachHangRepository.findAll();
    }

    @Override
    public KhachHang getById(Long id) {
        return khachHangRepository.findById(id).orElse(null);
    }

    @Override
    public KhachHang save(KhachHang khachHang) {
        return khachHangRepository.save(khachHang);
    }

    @Override
    public void delete(Long id) {
        khachHangRepository.deleteById(id);
    }
    @Override
    public Optional<KhachHang> login(String email, String matKhau) {
        return khachHangRepository.findByEmailAndMatKhau(email, matKhau);
    }

    @Override
    public String register(KhachHang khachHang) {
        Optional<KhachHang> existingKhachHang = khachHangRepository.findByEmail(khachHang.getEmail());
        if (existingKhachHang.isPresent()) {
            return "Email đã tồn tại, vui lòng dùng email khác!";
        }

        // Nếu chưa tồn tại, set ngày đăng ký hiện tại
        khachHang.setNgayDangKy(LocalDate.now());

        // (Bạn có thể mã hóa mật khẩu ở đây trước khi lưu)
        // Ví dụ: khachHang.setMatKhau(passwordEncoder.encode(khachHang.getMatKhau()));

        khachHangRepository.save(khachHang);
        return "Đăng ký thành công!";
    }
    @Override
    public boolean emailExists(String email) {
        return khachHangRepository.findByEmail(email).isPresent();
    }
}
