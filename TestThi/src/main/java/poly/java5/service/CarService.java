package poly.java5.service;

import java.util.List;

import poly.java5.entity.Car;

public interface CarService {

	List<Car> findAll();
	
	List<Car> findByNameLike(String name);
	
	Car findByName(String name);
	
	void saveCar(Car car);
	
	void deleteById(int id);
}
