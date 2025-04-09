package poly.java5.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import poly.java5.entity.Car;

public interface CarDAO extends JpaRepository<Car, Integer> {

	List<Car> findByNameLike(String name);
	
	Car findByName(String name);
	
	void deleteById(int id);
}
