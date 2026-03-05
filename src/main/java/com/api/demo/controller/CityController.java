package com.api.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.demo.dto.CityDTO;
import com.api.demo.repository.CityRepository;

@RestController
@RequestMapping("/cities")
public class CityController {

    private final CityRepository cityRepository;

    public CityController(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @GetMapping
    public List<CityDTO> getAllCities() {
        return cityRepository.findAll().stream().map(CityDTO::new).toList();
    }
}