package poly.java5.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data

@NamedQueries({
	@NamedQuery(name = "findByPrice", 
			query = "FROM Product o WHERE o.unitPrice BETWEEN ?1 AND ?2"),
	@NamedQuery(name = "findByKeyword", 
			query = "FROM Product o WHERE o.name LIKE ?1")
})
@Entity
@Table(name = "Products")
public class Product {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	@Default
	private String image = "product.png";
	@Column(name = "unitprice")
	private double unitPrice;
	@Default
	private boolean available = true;
	@Default
	@Temporal(TemporalType.DATE)
	@Column(name = "productdate")
	private Date productDate = new Date();
	@Default
	private String description = "";
	
	@ManyToOne
	@JoinColumn(name = "categoryid")
	private Category category;
	
	@OneToMany(mappedBy = "product")
	private List<OrderDetail> orderDetails;
}