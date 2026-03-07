package com.api.demo.service;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.api.demo.dto.CompanyDTO;
import com.api.demo.entity.City;
import com.api.demo.entity.Company;
import com.api.demo.entity.Segment;
import com.api.demo.repository.CityRepository;
import com.api.demo.repository.CompanyRepository;
import com.api.demo.repository.SegmentRepository;

@ExtendWith(MockitoExtension.class)
class CompanyServiceTest {

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private CityRepository cityRepository;

    @Mock
    private SegmentRepository segmentRepository;

    @InjectMocks
    private CompanyService companyService;

    @Test
    void testFindAll() {
        Company company = new Company(1L, "Company", "Responsible", new City(), "email@test.com", new Segment(), true);
        when(companyRepository.findAll()).thenReturn(List.of(company));

        List<CompanyDTO> result = companyService.findAll();

        assertEquals(1, result.size());
        assertEquals("Company", result.get(0).getName());
    }

    @Test
    void testFindById() {
        Company company = new Company(1L, "Company", "Responsible", new City(), "email@test.com", new Segment(), true);
        when(companyRepository.findById(1L)).thenReturn(Optional.of(company));

        Optional<CompanyDTO> result = companyService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals("Company", result.get().getName());
    }

    @Test
    void testFindByIdNotFound() {
        when(companyRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<CompanyDTO> result = companyService.findById(1L);

        assertFalse(result.isPresent());
    }

    @Test
    void testSave() {
        CompanyDTO dto = new CompanyDTO(null, "Company", "Responsible", 1L, "email@test.com", 1L, true);
        City city = new City(1L, 123, "City");
        Segment segment = new Segment(1L, "Segment");
        Company savedCompany = new Company(1L, "Company", "Responsible", city, "email@test.com", segment, true);

        when(cityRepository.findById(1L)).thenReturn(Optional.of(city));
        when(segmentRepository.findById(1L)).thenReturn(Optional.of(segment));
        when(companyRepository.save(any(Company.class))).thenReturn(savedCompany);

        CompanyDTO result = companyService.save(dto);

        assertEquals("Company", result.getName());
        verify(companyRepository).save(any(Company.class));
    }

    @Test
    void testSaveCityNotFound() {
        CompanyDTO dto = new CompanyDTO(null, "Company", "Responsible", 1L, "email@test.com", 1L, true);
        when(cityRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> companyService.save(dto));
    }

    @Test
    void testSaveSegmentNotFound() {
        CompanyDTO dto = new CompanyDTO(null, "Company", "Responsible", 1L, "email@test.com", 1L, true);
        when(cityRepository.findById(1L)).thenReturn(Optional.of(new City()));
        when(segmentRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> companyService.save(dto));
    }

    @Test
    void testDeleteById() {
        companyService.deleteById(1L);

        verify(companyRepository).deleteById(1L);
    }
}