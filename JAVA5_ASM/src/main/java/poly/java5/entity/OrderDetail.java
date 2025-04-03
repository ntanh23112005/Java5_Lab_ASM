package poly.java5.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data

@Entity
@Table(name = "Orderdetails",
		uniqueConstraints = {@UniqueConstraint(columnNames = {"orderid","productid"})})
public class OrderDetail {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int quantity;
	@Column(name = "unitprice")
	private double unitPrice;
	@ManyToOne
	@JoinColumn(name = "productid")
	private Product product;
	@ManyToOne
	@JoinColumn(name = "orderid")
	private Order  order;
	
	public Double getAmount() {
		return this.unitPrice*this.quantity;
	}
	
	 @Override
	    public String toString() {
	        return "OrderDetail{" +
	                "id=" + id +
	                ", quantity=" + quantity +
	                '}';
	    }
}