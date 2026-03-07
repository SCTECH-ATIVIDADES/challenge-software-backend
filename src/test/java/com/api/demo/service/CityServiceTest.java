package com.api.demo.service;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import com.api.demo.dto.IbgeMunicipioDTO;
import com.api.demo.entity.City;
import com.api.demo.repository.CityRepository;

@ExtendWith(MockitoExtension.class)
class CityServiceTest {

    @Mock
    private CityRepository cityRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CityService cityService;

    @Test
    void testSyncCities() {
        IbgeMunicipioDTO[] municipios = {
            new IbgeMunicipioDTO(),
            new IbgeMunicipioDTO()
        };
        municipios[0].setId(123);
        municipios[0].setNome("City1");
        municipios[1].setId(456);
        municipios[1].setNome("City2");

        when(restTemplate.getForObject(anyString(), eq(IbgeMunicipioDTO[].class))).thenReturn(municipios);
        when(cityRepository.findByCode(123)).thenReturn(Optional.empty());
        when(cityRepository.findByCode(456)).thenReturn(Optional.of(new City()));

        cityService.syncCities();

        @SuppressWarnings("unchecked")
        ArgumentCaptor<Iterable<City>> captor = ArgumentCaptor.forClass(Iterable.class);
        verify(cityRepository).saveAll(captor.capture());
        Iterable<City> savedCities = captor.getValue();
        assertTrue(savedCities.iterator().hasNext());
        City city = savedCities.iterator().next();
        assertEquals(123, city.getCode());
        assertEquals("City1", city.getName());
    }

    @Test
    void testSyncCitiesNullResponse() {
        when(restTemplate.getForObject(anyString(), eq(IbgeMunicipioDTO[].class))).thenReturn(null);

        cityService.syncCities();

        verify(cityRepository, never()).saveAll(any());
    }
}