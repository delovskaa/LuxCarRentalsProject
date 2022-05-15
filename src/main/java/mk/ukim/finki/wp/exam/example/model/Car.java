package mk.ukim.finki.wp.exam.example.model;


import javax.persistence.*;
import java.util.List;

@Entity
public class Car {

    public Car() {
    }

    public Car(String model, Double pricePerDay, Integer yearOfProduction, List<Category> categories) {
        this.model = model;
        this.pricePerDay = pricePerDay;
        this.yearOfProduction = yearOfProduction;
        this.categories = categories;
    }

    @Id
    @GeneratedValue
    private Long id;

    private String model;

    private Double pricePerDay;

    private Integer yearOfProduction;

    @ManyToOne
    private Manufacturer manufacturer;

    @ManyToMany
    private List<Category> categories;

    @ManyToOne
    private User creator;

    private boolean availability;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(Double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public Integer getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(Integer yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> category) {
        this.categories = category;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
