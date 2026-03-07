package com.api.demo.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

import com.api.demo.entity.City;

class CityDTOTest {

    @Test
    void testNoArgsConstructor() {
        CityDTO dto = new CityDTO();
        assertNotNull(dto);
    }

    @Test
    void testAllArgsConstructor() {
        CityDTO dto = new CityDTO(1L, 123, "City");
        assertEquals(1L, dto.getId());
        assertEquals(123, dto.getCode());
        assertEquals("City", dto.getName());
    }

    @Test
    void testConstructorFromEntity() {
        City city = new City(1L, 123, "City");
        CityDTO dto = new CityDTO(city);
        assertEquals(1L, dto.getId());
        assertEquals(123, dto.getCode());
        assertEquals("City", dto.getName());
    }

    @Test
    void testSettersAndGetters() {
        CityDTO dto = new CityDTO();
        dto.setId(2L);
        dto.setCode(456);
        dto.setName("Another City");

        assertEquals(2L, dto.getId());
        assertEquals(456, dto.getCode());
        assertEquals("Another City", dto.getName());
    }
}