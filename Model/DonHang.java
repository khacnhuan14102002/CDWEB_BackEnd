import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class DonHang {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maDH;

    @ManyToOne
    @JoinColumn(name = "maKH")
    private KhachHang khachHang;

    private Date ngayDat;
    private Double tongTien;
    private String trangThai;
    private String phuongThucThanhToan;

    @OneToMany(mappedBy = "donHang")
    private List<ChiTietDonHang> chiTietDonHangs;
}