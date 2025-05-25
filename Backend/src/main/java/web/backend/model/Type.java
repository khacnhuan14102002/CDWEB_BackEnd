package web.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Type {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long maType;

        private String tenType;

    public Long getMaType() {
        return maType;
    }

    public void setMaType(Long maType) {
        this.maType = maType;
    }

    public String getTenType() {
        return tenType;
    }

    public void setTenType(String tenType) {
        this.tenType = tenType;
    }


}
