package web.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cau_hoi")
public class CauHoi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long maSP;

    private String noiDung;

    private String traLoi;

    public CauHoi() {
    }

    public CauHoi(Long maSP, String noiDung, String traLoi) {
        this.maSP = maSP;
        this.noiDung = noiDung;
        this.traLoi = traLoi;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMaSP() {
        return maSP;
    }

    public void setMaSP(Long maSP) {
        this.maSP = maSP;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getTraLoi() {
        return traLoi;
    }

    public void setTraLoi(String traLoi) {
        this.traLoi = traLoi;
    }
}