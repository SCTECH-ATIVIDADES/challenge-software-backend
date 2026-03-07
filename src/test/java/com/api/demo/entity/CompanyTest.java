package com.api.demo.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

class CompanyTest {

    @Test
    void testNoArgsConstructor() {
        Company company = new Company();
        assertNotNull(company);
    }

    @Test
    void testAllArgsConstructor() {
        City city = new City(1L, 123, "City");
        Segment segment = new Segment(1L, "Segment");
        Company company = new Company(1L, "Company", "Responsible", city, "email@test.com", segment, true);
        assertEquals(1L, company.getId());
        assertEquals("Company", company.getName());
        assertEquals("Responsible", company.getResponsibleName());
        assertEquals(city, company.getCity());
        assertEquals("email@test.com", company.getEmail());
        assertEquals(segment, company.getSegment());
        assertEquals(true, company.getStatus());
    }

    @Test
    void testSettersAndGetters() {
        Company company = new Company();
        City city = new City(1L, 123, "City");
        Segment segment = new Segment(1L, "Segment");
        company.setId(2L);
        company.setName("Test Company");
        company.setResponsibleName("Test Responsible");
        company.setCity(city);
        company.setEmail("test@email.com");
        company.setSegment(segment);
        company.setStatus(false);

        assertEquals(2L, company.getId());
        assertEquals("Test Company", company.getName());
        assertEquals("Test Responsible", company.getResponsibleName());
        assertEquals(city, company.getCity());
        assertEquals("test@email.com", company.getEmail());
        assertEquals(segment, company.getSegment());
        assertEquals(false, company.getStatus());
    }
}