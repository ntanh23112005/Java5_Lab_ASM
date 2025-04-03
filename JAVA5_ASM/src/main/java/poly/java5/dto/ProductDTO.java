package poly.java5.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor @Getter
@Setter
public class ProductDTO {

	private int id;
	private String name;
	private String image;
	private double unitPrice;
	private java.sql.Date productDate;
	private int inStock;
	private String description;
	private String categoryId;
}
