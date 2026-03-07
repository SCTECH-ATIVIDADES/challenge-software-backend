package com.api.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.api.demo.dto.SegmentDTO;
import com.api.demo.entity.Segment;
import com.api.demo.repository.SegmentRepository;

@Service
public class SegmentService {

    private final SegmentRepository segmentRepository;

    public SegmentService(SegmentRepository segmentRepository) {
        this.segmentRepository = segmentRepository;
    }

    public List<SegmentDTO> findAll() {
        return segmentRepository.findAll().stream().map(SegmentDTO::new).toList();
    }

    public Optional<SegmentDTO> findById(Long id) {
        return segmentRepository.findById(id).map(SegmentDTO::new);
    }

    public SegmentDTO save(SegmentDTO segmentDTO) {
        // Check if name already exists
        Optional<Segment> existing = segmentRepository.findByName(segmentDTO.getName());
        if (existing.isPresent() && !existing.get().getId().equals(segmentDTO.getId())) {
            throw new IllegalArgumentException("Segment name already exists: " + segmentDTO.getName());
        }

        Segment segment = new Segment();
        segment.setId(segmentDTO.getId());
        segment.setName(segmentDTO.getName());
        Segment saved = segmentRepository.save(segment);
        return new SegmentDTO(saved);
    }

    public void deleteById(Long id) {
        segmentRepository.deleteById(id);
    }
}