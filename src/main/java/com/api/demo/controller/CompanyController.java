package com.api.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.demo.dto.CompanyDTO;
import com.api.demo.service.CompanyService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/companies")
@Tag(name = "Empresas", description = "Endpoints para gerenciamento de empresas")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    @Operation(summary = "Listar empresas", description = "Lista todas as empresas cadastradas")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de empresas retornada com sucesso")
    })
    public List<CompanyDTO> getAllCompanies() {
        return companyService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar empresa por ID", description = "Busca uma empresa específica por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Empresa encontrada"),
        @ApiResponse(responseCode = "404", description = "Empresa não encontrada")
    })
    public ResponseEntity<CompanyDTO> getCompanyById(@Parameter(description = "ID da empresa") @PathVariable Long id) {
        return companyService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Criar empresa", description = "Cria uma nova empresa")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Empresa criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public CompanyDTO createCompany(@ModelAttribute CompanyDTO companyDTO) {
        return companyService.save(companyDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar empresa", description = "Atualiza uma empresa existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Empresa atualizada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Empresa não encontrada")
    })
    public ResponseEntity<CompanyDTO> updateCompany(
            @Parameter(description = "ID da empresa") @PathVariable Long id,
            @ModelAttribute CompanyDTO companyDTO) {
        companyDTO.setId(id);
        try {
            CompanyDTO updated = companyService.save(companyDTO);
            return ResponseEntity.ok(updated);
        } catch (Exception _) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar empresa", description = "Remove uma empresa")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Empresa removida com sucesso"),
        @ApiResponse(responseCode = "404", description = "Empresa não encontrada")
    })
    public ResponseEntity<Void> deleteCompany(@Parameter(description = "ID da empresa") @PathVariable Long id) {
        companyService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}