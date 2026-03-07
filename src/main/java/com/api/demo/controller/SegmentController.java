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

@RestController
@RequestMapping("/segments")
public class SegmentController {

    private final SegmentService segmentService;

    public SegmentController(SegmentService segmentService) {
        this.segmentService = segmentService;
    }

    @GetMapping
    public List<SegmentDTO> getAllSegments() {
        return segmentService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SegmentDTO> getSegmentById(@PathVariable Long id) {
        return segmentService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createSegment(@ModelAttribute SegmentDTO segmentDTO) {
        try {
            SegmentDTO created = segmentService.save(segmentDTO);
            return ResponseEntity.ok(created);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSegment(
            @PathVariable Long id,
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
    public ResponseEntity<Void> deleteSegment(@PathVariable Long id) {
        segmentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}