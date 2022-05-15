package mk.ukim.finki.wp.exam.example.web;

import mk.ukim.finki.wp.exam.example.model.Car;
import mk.ukim.finki.wp.exam.example.model.Category;
import mk.ukim.finki.wp.exam.example.model.Manufacturer;
import mk.ukim.finki.wp.exam.example.service.CarService;
import mk.ukim.finki.wp.exam.example.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CarController {

    private final CarService service;
    private final CategoryService categoryService;

    public CarController(CarService service, CategoryService categoryService) {
        this.service = service;
        this.categoryService = categoryService;
    }


    @GetMapping({"/","/cars"})
    public String showCars(@RequestParam(required = false) String modelSearch, @RequestParam(required = false) Long categoryId, Model model) {
        List<Car> cars;
        List<Category> categories = this.categoryService.listAll();
        if (modelSearch == null && categoryId == null) {
            cars = this.service.listAllCars();
        } else {
            cars = this.service.listCarsByModelAndCategory(modelSearch, categoryId);
        }
        model.addAttribute("categories", categories);
        model.addAttribute("cars", cars);
        return "list";
    }


    @GetMapping("/cars/add")
    public String showAdd(Model model) {
        List<Category> categories = this.categoryService.listAll();
        model.addAttribute("categories", categories);
        return "form";
    }

    @GetMapping("/cars/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model) {
        Car car = this.service.findById(id);
        List<Category> categories = this.categoryService.listAll();
        model.addAttribute("categories", categories);
        model.addAttribute("car", car);
        return "form";
    }


    @PostMapping("/cars")
    public String create(@RequestParam String model,
                         @RequestParam Double pricePerDay,
                         @RequestParam Integer yearOfProduction,
                         @RequestParam List<Long> categories) {
        this.service.create(model, pricePerDay, yearOfProduction, categories);
        return "redirect:/cars";
    }

    @PostMapping("/cars/{id}")
    public String update(@PathVariable Long id,
                         @RequestParam String model,
                         @RequestParam Double pricePerDay,
                         @RequestParam Integer yearOfProduction,
                         @RequestParam List<Long> categories) {
        this.service.update(id, model, pricePerDay, yearOfProduction, categories);
        return "redirect:/cars";
    }

    @PostMapping("/cars/{id}/delete")
    public String delete(@PathVariable Long id) {
        this.service.delete(id);
        return "redirect:/cars";
    }
}
