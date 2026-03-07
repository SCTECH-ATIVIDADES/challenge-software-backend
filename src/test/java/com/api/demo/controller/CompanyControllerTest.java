package com.api.demo.controller;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.api.demo.dto.CompanyDTO;
import com.api.demo.service.CompanyService;

@ExtendWith(MockitoExtension.class)
class CompanyControllerTest {

    @Mock
    private CompanyService companyService;

    @InjectMocks
    private CompanyController companyController;

    @Test
    void testGetAllCompanies() {
        CompanyDTO dto = new CompanyDTO(1L, "Company", "Responsible", 1L, "email@test.com", 1L, true);
        when(companyService.findAll()).thenReturn(List.of(dto));

        List<CompanyDTO> result = companyController.getAllCompanies();

        assertEquals(1, result.size());
        assertEquals("Company", result.get(0).getName());
    }

    @Test
    void testGetCompanyById() {
        CompanyDTO dto = new CompanyDTO(1L, "Company", "Responsible", 1L, "email@test.com", 1L, true);
        when(companyService.findById(1L)).thenReturn(Optional.of(dto));

        var result = companyController.getCompanyById(1L);

        assertTrue(result.getBody() != null);
        assertEquals("Company", result.getBody().getName());
    }

    @Test
    void testGetCompanyByIdNotFound() {
        when(companyService.findById(1L)).thenReturn(Optional.empty());

        var result = companyController.getCompanyById(1L);

        assertEquals(404, result.getStatusCode().value());
    }

    @Test
    void testCreateCompany() {
        CompanyDTO input = new CompanyDTO(null, "Company", "Responsible", 1L, "email@test.com", 1L, true);
        CompanyDTO output = new CompanyDTO(1L, "Company", "Responsible", 1L, "email@test.com", 1L, true);
        when(companyService.save(any(CompanyDTO.class))).thenReturn(output);

        CompanyDTO result = companyController.createCompany(input);

        assertEquals(1L, result.getId());
        assertEquals("Company", result.getName());
    }

    @Test
    void testUpdateCompany() {
        CompanyDTO input = new CompanyDTO(1L, "Updated Company", "Responsible", 1L, "email@test.com", 1L, true);
        CompanyDTO output = new CompanyDTO(1L, "Updated Company", "Responsible", 1L, "email@test.com", 1L, true);
        when(companyService.save(any(CompanyDTO.class))).thenReturn(output);

        var result = companyController.updateCompany(1L, input);

        assertTrue(result.getBody() != null);
        assertEquals("Updated Company", result.getBody().getName());
    }

    @Test
    void testDeleteCompany() {
        doNothing().when(companyService).deleteById(1L);

        companyController.deleteCompany(1L);

        verify(companyService).deleteById(1L);
    }
}