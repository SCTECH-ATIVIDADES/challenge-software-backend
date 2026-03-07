package com.api.demo.controller;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.test.context.support.WithMockUser;

import com.api.demo.dto.CityDTO;
import com.api.demo.entity.City;
import com.api.demo.repository.CityRepository;

@ExtendWith(MockitoExtension.class)
class CityControllerTest {

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private CityController cityController;

    @Test
    @WithMockUser
    void testGetAllCities() {
        City city = new City(1L, 123, "City");
        when(cityRepository.findAll()).thenReturn(List.of(city));

        List<CityDTO> result = cityController.getAllCities();

        assertEquals(1, result.size());
    }
}