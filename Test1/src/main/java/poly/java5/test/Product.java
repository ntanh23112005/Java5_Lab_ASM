package poly.java5.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Product {
String id;
String name;
Double unitPrice;
public Product(String id, String name, Double unitPrice) {
	super();
	this.id = id;
	this.name = name;
	this.unitPrice = unitPrice;
}
public Product() {
	super();
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Double getUnitPrice() {
	return unitPrice;
}
public void setUnitPrice(Double unitPrice) {
	this.unitPrice = unitPrice;
}


}
