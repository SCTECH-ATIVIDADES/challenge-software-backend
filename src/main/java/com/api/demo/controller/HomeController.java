package com.api.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Home", description = "Endpoints de teste da aplicação")
public class HomeController {

    @GetMapping("/test")
    @Operation(summary = "Teste de serviço", description = "Endpoint de teste da aplicação")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Serviço funcionando corretamente")
    })
    public String test() {
        return "OK";
    }

}