package com.api.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.demo.dto.CityDTO;
import com.api.demo.repository.CityRepository;
import com.api.demo.service.CityService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/cities")
@Tag(name = "Cidades", description = "Endpoints para gerenciamento de cidades")
public class CityController {

    private final CityRepository cityRepository;
    private final CityService cityService;

    public CityController(CityRepository cityRepository, CityService cityService) {
        this.cityRepository = cityRepository;
        this.cityService = cityService;
    }

    @GetMapping
    @Operation(summary = "Listar cidades", description = "Lista todas as cidades cadastradas")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de cidades retornada com sucesso")
    })
    public List<CityDTO> getAllCities() {
        return cityRepository.findAll().stream().map(CityDTO::new).toList();
    }

    @PostMapping("/sync")
    @Operation(summary = "Sincronizar cidades do IBGE", 
        description = "Sincroniza cidades de Santa Catarina da API oficial do IBGE")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cidades sincronizadas com sucesso")
    })
    public String syncCities() {
        cityService.syncCities();
        return "Cities synchronized successfully";
    }
}