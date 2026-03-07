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

import com.api.demo.dto.SegmentDTO;
import com.api.demo.service.SegmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/segments")
@Tag(name = "Segmentos", description = "Endpoints para gerenciamento de segmentos")
public class SegmentController {

    private final SegmentService segmentService;

    public SegmentController(SegmentService segmentService) {
        this.segmentService = segmentService;
    }

    @GetMapping
    @Operation(summary = "Listar segmentos", description = "Lista todos os segmentos cadastrados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de segmentos retornada com sucesso")
    })
    public List<SegmentDTO> getAllSegments() {
        return segmentService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar segmento por ID", description = "Busca um segmento específico por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Segmento encontrado"),
        @ApiResponse(responseCode = "404", description = "Segmento não encontrado")
    })
    public ResponseEntity<SegmentDTO> getSegmentById(@Parameter(description = "ID do segmento") @PathVariable Long id) {
        return segmentService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Criar segmento", description = "Cria um novo segmento")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Segmento criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos", 
            content = @Content(mediaType = "text/plain"))
    })
    public ResponseEntity<?> createSegment(@ModelAttribute SegmentDTO segmentDTO) {
        try {
            SegmentDTO created = segmentService.save(segmentDTO);
            return ResponseEntity.ok(created);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar segmento", description = "Atualiza um segmento existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Segmento atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos", 
            content = @Content(mediaType = "text/plain")),
        @ApiResponse(responseCode = "404", description = "Segmento não encontrado")
    })
    public ResponseEntity<?> updateSegment(
            @Parameter(description = "ID do segmento") @PathVariable Long id,
            @ModelAttribute SegmentDTO segmentDTO) {
        segmentDTO.setId(id);
        try {
            SegmentDTO updated = segmentService.save(segmentDTO);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar segmento", description = "Remove um segmento")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Segmento removido com sucesso"),
        @ApiResponse(responseCode = "404", description = "Segmento não encontrado")
    })
    public ResponseEntity<Void> deleteSegment(@Parameter(description = "ID do segmento") @PathVariable Long id) {
        segmentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}