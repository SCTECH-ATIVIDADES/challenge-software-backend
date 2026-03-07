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

import com.api.demo.dto.SegmentDTO;
import com.api.demo.entity.Segment;
import com.api.demo.repository.SegmentRepository;

@ExtendWith(MockitoExtension.class)
class SegmentServiceTest {

    @Mock
    private SegmentRepository segmentRepository;

    @InjectMocks
    private SegmentService segmentService;

    @Test
    void testFindAll() {
        Segment segment = new Segment(1L, "Segment");
        when(segmentRepository.findAll()).thenReturn(List.of(segment));

        List<SegmentDTO> result = segmentService.findAll();

        assertEquals(1, result.size());
        assertEquals("Segment", result.get(0).getName());
    }

    @Test
    void testFindById() {
        Segment segment = new Segment(1L, "Segment");
        when(segmentRepository.findById(1L)).thenReturn(Optional.of(segment));

        Optional<SegmentDTO> result = segmentService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals("Segment", result.get().getName());
    }

    @Test
    void testFindByIdNotFound() {
        when(segmentRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<SegmentDTO> result = segmentService.findById(1L);

        assertFalse(result.isPresent());
    }

    @Test
    void testSaveNew() {
        SegmentDTO dto = new SegmentDTO(null, "New Segment");
        Segment savedSegment = new Segment(1L, "New Segment");

        when(segmentRepository.findByName("New Segment")).thenReturn(Optional.empty());
        when(segmentRepository.save(any(Segment.class))).thenReturn(savedSegment);

        SegmentDTO result = segmentService.save(dto);

        assertEquals("New Segment", result.getName());
        verify(segmentRepository).save(any(Segment.class));
    }

    @Test
    void testSaveUpdate() {
        SegmentDTO dto = new SegmentDTO(1L, "Updated Segment");
        Segment savedSegment = new Segment(1L, "Updated Segment");

        when(segmentRepository.findByName("Updated Segment")).thenReturn(Optional.empty());
        when(segmentRepository.save(any(Segment.class))).thenReturn(savedSegment);

        SegmentDTO result = segmentService.save(dto);

        assertEquals("Updated Segment", result.getName());
    }

    @Test
    void testSaveNameExists() {
        SegmentDTO dto = new SegmentDTO(null, "Existing Segment");
        Segment existing = new Segment(2L, "Existing Segment");

        when(segmentRepository.findByName("Existing Segment")).thenReturn(Optional.of(existing));

        assertThrows(IllegalArgumentException.class, () -> segmentService.save(dto));
    }

    @Test
    void testSaveNameExistsSameId() {
        SegmentDTO dto = new SegmentDTO(1L, "Segment");
        Segment existing = new Segment(1L, "Segment");
        Segment saved = new Segment(1L, "Segment");

        when(segmentRepository.findByName("Segment")).thenReturn(Optional.of(existing));
        when(segmentRepository.save(any(Segment.class))).thenReturn(saved);

        SegmentDTO result = segmentService.save(dto);

        assertEquals("Segment", result.getName());
    }

    @Test
    void testDeleteById() {
        segmentService.deleteById(1L);

        verify(segmentRepository).deleteById(1L);
    }
}