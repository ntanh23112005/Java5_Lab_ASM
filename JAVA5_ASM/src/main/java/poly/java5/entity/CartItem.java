package poly.java5.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data

@Entity
@Table(name = "Cartitems",
		uniqueConstraints = @UniqueConstraint(columnNames = {"owner", "productid"}))
public class CartItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String owner;
	@ManyToOne
	@JoinColumn(name = "productid")
	private Product product;
	@Default
	private int quantity = 1;
	@Default
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdate")
	private Date createDate = new Date();
	
	public double getAmount(){
		return this.quantity*this.product.getUnitPrice();
	}
}