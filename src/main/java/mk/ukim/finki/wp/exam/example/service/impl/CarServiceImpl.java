package mk.ukim.finki.wp.exam.example.service.impl;

import mk.ukim.finki.wp.exam.example.model.Car;
import mk.ukim.finki.wp.exam.example.model.Category;
import mk.ukim.finki.wp.exam.example.model.Manufacturer;
import mk.ukim.finki.wp.exam.example.model.exceptions.InvalidCarIdException;
import mk.ukim.finki.wp.exam.example.model.exceptions.InvalidCategoryIdException;
import mk.ukim.finki.wp.exam.example.repository.CarRepository;
import mk.ukim.finki.wp.exam.example.repository.CategoryRepository;
import mk.ukim.finki.wp.exam.example.service.CarService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CategoryRepository categoryRepository;

    public CarServiceImpl(CarRepository carRepository, CategoryRepository categoryRepository) {
        this.carRepository = carRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Car> listAllCars() {
        return this.carRepository.findAll();
    }

    @Override
    public Car findById(Long id) {
        return this.carRepository.findById(id).orElseThrow(InvalidCarIdException::new);
    }

    @Override
    public Car create(String model, Double pricePerDay, Integer yearOfProduction, List<Long> categoryIds) {
        List<Category> categories = this.categoryRepository.findAllById(categoryIds);
        Car car = new Car(model, pricePerDay, yearOfProduction, categories);
        return this.carRepository.save(car);
    }

    @Override
    public Car update(Long id, String model, Double pricePerDay, Integer yearOfProduction, List<Long> categoryIds) {
        Car car = this.findById(id);
        car.setModel(model);
        car.setPricePerDay(pricePerDay);
        car.setYearOfProduction(yearOfProduction);
        List<Category> categories = this.categoryRepository.findAllById(categoryIds);
        car.setCategories(categories);

        return this.carRepository.save(car);
    }

    @Override
    public Car delete(Long id) {
        Car car = this.findById(id);
        this.carRepository.delete(car);
        return car;
    }

    @Override
    public List<Car> listCarsByModelAndCategory(String model, Long categoryId) {

        Category category = categoryId != null? this.categoryRepository.findById(categoryId).orElse((Category) null) : null;
        String modelLike="%"+model+"%";
        if(model != null && category != null) {
            return this.carRepository.findAllByModelLikeAndCategoriesContaining(modelLike, category);
        } else if(model!=null) {
            return this.carRepository.findAllByModelLike(modelLike);
        } else if (category!=null){
            return this.carRepository.findAllByCategoriesContaining(category);
        } else {
            return this.carRepository.findAll();
        }

    }
}
