package com.api.demo.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

import com.api.demo.entity.City;
import com.api.demo.entity.Company;
import com.api.demo.entity.Segment;

class CompanyDTOTest {

    @Test
    void testNoArgsConstructor() {
        CompanyDTO dto = new CompanyDTO();
        assertNotNull(dto);
    }

    @Test
    void testAllArgsConstructor() {
        CompanyDTO dto = new CompanyDTO(1L, "Company", "Responsible", 1L, "email@test.com", 1L, true);
        assertEquals(1L, dto.getId());
        assertEquals("Company", dto.getName());
        assertEquals("Responsible", dto.getResponsibleName());
        assertEquals(1L, dto.getCityId());
        assertEquals("email@test.com", dto.getEmail());
        assertEquals(1L, dto.getSegmentId());
        assertEquals(true, dto.getStatus());
    }

    @Test
    void testConstructorFromEntity() {
        City city = new City(1L, 123, "City");
        Segment segment = new Segment(1L, "Segment");
        Company company = new Company(1L, "Company", "Responsible", city, "email@test.com", segment, true);
        CompanyDTO dto = new CompanyDTO(company);
        assertEquals(1L, dto.getId());
        assertEquals("Company", dto.getName());
        assertEquals("Responsible", dto.getResponsibleName());
        assertEquals(1L, dto.getCityId());
        assertEquals("email@test.com", dto.getEmail());
        assertEquals(1L, dto.getSegmentId());
        assertEquals(true, dto.getStatus());
    }

    @Test
    void testSettersAndGetters() {
        CompanyDTO dto = new CompanyDTO();
        dto.setId(2L);
        dto.setName("Test Company");
        dto.setResponsibleName("Test Responsible");
        dto.setCityId(2L);
        dto.setEmail("test@email.com");
        dto.setSegmentId(2L);
        dto.setStatus(false);

        assertEquals(2L, dto.getId());
        assertEquals("Test Company", dto.getName());
        assertEquals("Test Responsible", dto.getResponsibleName());
        assertEquals(2L, dto.getCityId());
        assertEquals("test@email.com", dto.getEmail());
        assertEquals(2L, dto.getSegmentId());
        assertEquals(false, dto.getStatus());
    }
}