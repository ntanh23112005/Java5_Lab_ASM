package poly.java5.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "Products")
@NoArgsConstructor @AllArgsConstructor @Getter @Setter
@Data
public class Product {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String image;
    @Column(name = "unitprice")
    private double unitPrice;
    @Column(name = "productdate")
    private java.sql.Date productDate;
    @Column(name = "instock")
    private int inStock;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryid")
    private Category category;  // Ánh xạ với bảng Categories
    
    @Column(name = "trangthai")
    private boolean trangThai = true;  // Cột trạng thái mặc định là true
}
