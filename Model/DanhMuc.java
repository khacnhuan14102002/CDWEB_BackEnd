import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor
public class DanhMuc {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maDanhMuc;

    private String tenDanhMuc;

    @OneToMany(mappedBy = "danhMuc")
    private List<AnPham> sanPhams;
}