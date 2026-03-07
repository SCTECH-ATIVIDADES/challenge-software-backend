package com.api.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.api.demo.dto.IbgeMunicipioDTO;
import com.api.demo.entity.City;
import com.api.demo.repository.CityRepository;

@Service
public class CityService {

    private final CityRepository cityRepository;
    private final RestTemplate restTemplate;

    public CityService(CityRepository cityRepository, RestTemplate restTemplate) {
        this.cityRepository = cityRepository;
        this.restTemplate = restTemplate;
    }

    public void syncCities() {
        String url = "https://servicodados.ibge.gov.br/api/v1/localidades/estados/42/municipios";
        IbgeMunicipioDTO[] municipios = restTemplate.getForObject(url, IbgeMunicipioDTO[].class);
        if (municipios != null) {
            List<City> citiesToSave = new ArrayList<>();
            for (IbgeMunicipioDTO dto : municipios) {
                if (!cityRepository.findByCode(dto.getId()).isPresent()) {
                    citiesToSave.add(new City(null, dto.getId(), dto.getNome()));
                }
            }
            if (!citiesToSave.isEmpty()) {
                cityRepository.saveAll(citiesToSave);
            }
        }
    }
}