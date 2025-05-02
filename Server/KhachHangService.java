import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class KhachHangService {
    private final KhachHangRepository khachHangRepository;

    public KhachHangService(KhachHangRepository khachHangRepository) {
        this.khachHangRepository = khachHangRepository;
    }

    public List<KhachHang> getAllKhachHang() {
        return khachHangRepository.findAll();
    }

    public Optional<KhachHang> getKhachHangById(Long id) {
        return khachHangRepository.findById(id);
    }

    public KhachHang saveKhachHang(KhachHang khachHang) {
        return khachHangRepository.save(khachHang);
    }
}