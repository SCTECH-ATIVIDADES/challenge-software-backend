package com.api.demo.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.demo.security.JwtUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/login")
@Tag(name = "Autenticação", description = "Endpoints de autenticação")
public class LoginController {

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    public LoginController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    @Operation(summary = "Login", description = "Autentica o usuário e retorna um token JWT")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Login bem-sucedido", 
            content = @Content(mediaType = "application/json", 
                schema = @Schema(example = "{\"token\": \"eyJhbGciOiJIUzI1NiJ9...\"}"))),
        @ApiResponse(responseCode = "401", description = "Credenciais inválidas", 
            content = @Content(mediaType = "application/json", 
                schema = @Schema(example = "{\"error\": \"Invalid credentials\"}")))
    })
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> loginRequest) {
        String username = loginRequest.get("username");
        String password = loginRequest.get("password");

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            String token = jwtUtil.generateToken(username);
            return ResponseEntity.ok(Map.of("token", token));
        } catch (AuthenticationException _) {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials"));
        }
    }
}