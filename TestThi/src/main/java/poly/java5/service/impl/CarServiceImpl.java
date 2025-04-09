package poly.java5.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import poly.java5.dao.CarDAO;
import poly.java5.entity.Car;
import poly.java5.service.CarService;
@Service
public class CarServiceImpl implements CarService {

	@Autowired
	CarDAO dao;
	
	@Override
	public List<Car> findAll() {
		return dao.findAll();
	}

	@Override
	public List<Car> findByNameLike(String name) {
		return dao.findByNameLike(name);
	}

	@Override
	public Car findByName(String name) {
		return dao.findByName(name);
	}

	@Override
	public void saveCar(Car car) {
		dao.save(car);
	}

	@Override
	public void deleteById(int id) {
		dao.deleteById(id);
	}

}
