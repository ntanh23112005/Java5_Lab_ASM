package poly.java5.entity;
public interface RevenueByCategory {
	Category getCategory();
	double getMinPrice();
	double getMaxPrice();
	double getAveragePrice();
	int getQuantity();
	double getRevenue();
}
