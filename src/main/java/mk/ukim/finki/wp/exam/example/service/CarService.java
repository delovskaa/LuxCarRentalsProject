package mk.ukim.finki.wp.exam.example.service;

import mk.ukim.finki.wp.exam.example.model.Car;
import mk.ukim.finki.wp.exam.example.model.Manufacturer;

import java.util.List;


public interface CarService {


    List<Car> listAllCars();

    Car findById(Long id);

    Car create(String model, Double pricePerDay, Integer yearOfProduction, List<Long> categoryIds);

    Car update(Long id, String model, Double pricePerDay, Integer yearOfProduction, List<Long> categoryIds);

    Car delete(Long id);

    List<Car> listCarsByModelAndCategory(String model, Long categoryId);

}
