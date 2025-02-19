package poly.java5.entity;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

@Entity
@Table(name = "Orders")
public class Order {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Default
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(iso = ISO.DATE_TIME, fallbackPatterns = {"MM/dd/yyyy", "yyyy-MM-dd", "dd-MM-yyyy"})
	@Column(name = "orderdate")
	private Date orderDate = new Date();
	@Column(name = "shippingaddress")
	private String shippingAddress;
	@Column(name = "orderstatus")
	private int orderStatus;
	@ManyToOne
	@JoinColumn(name = "username")
	private User user;
	@OneToMany(mappedBy = "order")
	private List<OrderDetail> orderDetails;
	
	public Integer getTotalQuantity() {
		return this.orderDetails.stream().mapToInt(d -> d.getQuantity()).sum();
	}
	public Double getTotalAmount() {
		return this.orderDetails.stream().mapToDouble(d -> d.getAmount()).sum();
	}
}