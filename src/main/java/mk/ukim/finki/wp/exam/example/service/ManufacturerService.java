package mk.ukim.finki.wp.exam.example.service;

import mk.ukim.finki.wp.exam.example.model.Manufacturer;

import java.util.List;

public interface ManufacturerService {

    Manufacturer findById(Long id);

    List<Manufacturer> findAll();

    Manufacturer create(String name, String countryOfOrigin);

    Manufacturer delete(Long id);

}
