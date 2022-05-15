package mk.ukim.finki.wp.exam.example.repository;

import mk.ukim.finki.wp.exam.example.model.Car;
import mk.ukim.finki.wp.exam.example.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findAllByModelLikeAndCategoriesContaining(String model, Category category);
    List<Car> findAllByModelLike(String model);
    List<Car> findAllByCategoriesContaining(Category category);

}
