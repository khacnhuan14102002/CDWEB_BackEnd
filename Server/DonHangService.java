import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DonHangService {
    private final DonHangRepository donHangRepository;

    public DonHangService(DonHangRepository donHangRepository) {
        this.donHangRepository = donHangRepository;
    }

    public List<DonHang> getAllDonHang() {
        return donHangRepository.findAll();
    }

    public Optional<DonHang> getDonHangById(Long id) {
        return donHangRepository.findById(id);
    }

    public DonHang saveDonHang(DonHang donHang) {
        return donHangRepository.save(donHang);
    }
}