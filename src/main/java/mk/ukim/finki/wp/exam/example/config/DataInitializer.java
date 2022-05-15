package mk.ukim.finki.wp.exam.example.config;

import mk.ukim.finki.wp.exam.example.model.Role;
import mk.ukim.finki.wp.exam.example.model.User;
import mk.ukim.finki.wp.exam.example.service.CategoryService;
import mk.ukim.finki.wp.exam.example.service.CarService;
import mk.ukim.finki.wp.exam.example.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class DataInitializer {

    public static final String ADMIN = "admin";

    private final UserService userService;

    private final CategoryService categoryService;

    private final CarService carService;

    public DataInitializer(UserService userService, CategoryService categoryService, CarService carService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.carService = carService;
    }


    @PostConstruct
    public void initData() {
        User admin = this.userService.create(ADMIN, ADMIN, ADMIN, ADMIN, Role.ROLE_ADMIN);

        for (int i = 1; i < 6; i++) {
            this.categoryService.create("Category " + i);
        }

        for (int i = 1; i < 11; i++) {
            this.carService.create("Car " + i, 20.9 * i, i, Stream.of(1L, i % 5L + 1).collect(Collectors.toList()));
        }
    }
}
