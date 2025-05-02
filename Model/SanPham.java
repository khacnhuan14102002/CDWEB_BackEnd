import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class AnPham {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maSP;

    private String tenSP;
    private String loai;
    private String moTa;
    private Double gia;
    private Integer soLuong;
    private String hinhAnh;
    private String doiTuong;

    @ManyToOne
    @JoinColumn(name = "maDanhMuc")
    private DanhMuc danhMuc;
}