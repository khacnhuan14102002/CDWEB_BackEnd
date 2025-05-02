import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ChiTietDonHang {
    @EmbeddedId
    private ChiTietDonHangId id;

    @ManyToOne
    @MapsId("maDH")
    @JoinColumn(name = "maDH")
    private DonHang donHang;

    @ManyToOne
    @MapsId("maSP")
    @JoinColumn(name = "maSP")
    private AnPham anPham;

    private Integer soLuong;
    private Double donGia;
}