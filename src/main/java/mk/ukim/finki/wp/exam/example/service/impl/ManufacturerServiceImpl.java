package mk.ukim.finki.wp.exam.example.service.impl;

import mk.ukim.finki.wp.exam.example.model.Manufacturer;
import mk.ukim.finki.wp.exam.example.model.exceptions.InvalidManufacturerIdException;
import mk.ukim.finki.wp.exam.example.repository.ManufacturerRepository;
import mk.ukim.finki.wp.exam.example.service.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public Manufacturer findById(Long id) {
        return this.manufacturerRepository.findById(id).orElseThrow(InvalidManufacturerIdException::new);
    }

    @Override
    public List<Manufacturer> findAll() {
        return this.manufacturerRepository.findAll();
    }

    @Override
    public Manufacturer create(String name, String countryOfOrigin) {
        Manufacturer manufacturer = new Manufacturer(name, countryOfOrigin);
        return this.manufacturerRepository.save(manufacturer);
    }

    @Override
    public Manufacturer delete(Long id) {
        Manufacturer manufacturer = this.findById(id);
        this.manufacturerRepository.delete(manufacturer);
        return manufacturer;
    }
}
