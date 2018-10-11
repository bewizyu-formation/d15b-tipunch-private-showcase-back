package fr.formation.controller;

import fr.formation.model.City;
import fr.formation.repository.CityRepositoryImpl;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/citys")
public class CityController {
    private CityRepositoryImpl cityRepository;

    public CityController(CityRepositoryImpl cityRepository) {
        this.cityRepository = cityRepository;
    }

    //@Secured({"ROLE_ARTIST", "ROLE_USER"})
    @GetMapping()
    public List<City> findAll() {
        return cityRepository.findAll();
    }

    //@Secured({"ROLE_ARTIST", "ROLE_USER"})
    @GetMapping("/{cityId}")
    public City findOne(@PathVariable Integer cityId) {
        return cityRepository.findOne(cityId);
    }
}



