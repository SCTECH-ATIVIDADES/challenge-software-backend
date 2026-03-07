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
import org.springframework.security.test.context.support.WithMockUser;

import com.api.demo.dto.SegmentDTO;
import com.api.demo.service.SegmentService;

@ExtendWith(MockitoExtension.class)
class SegmentControllerTest {

    @Mock
    private SegmentService segmentService;

    @InjectMocks
    private SegmentController segmentController;

    @Test
    @WithMockUser
    void testGetAllSegments() {
        SegmentDTO dto = new SegmentDTO(1L, "Segment");
        when(segmentService.findAll()).thenReturn(List.of(dto));

        List<SegmentDTO> result = segmentController.getAllSegments();

        assertEquals(1, result.size());
        assertEquals("Segment", result.get(0).getName());
    }

    @Test
    @WithMockUser
    void testGetSegmentById() {
        SegmentDTO dto = new SegmentDTO(1L, "Segment");
        when(segmentService.findById(1L)).thenReturn(Optional.of(dto));

        var result = segmentController.getSegmentById(1L);

        assertTrue(result.getBody() != null);
        assertEquals("Segment", result.getBody().getName());
    }

    @Test
    @WithMockUser
    void testGetSegmentByIdNotFound() {
        when(segmentService.findById(1L)).thenReturn(Optional.empty());

        var result = segmentController.getSegmentById(1L);

        assertEquals(404, result.getStatusCode().value());
    }

    @Test
    @WithMockUser
    void testCreateSegment() {
        SegmentDTO input = new SegmentDTO(null, "New Segment");
        SegmentDTO output = new SegmentDTO(1L, "New Segment");
        when(segmentService.save(any(SegmentDTO.class))).thenReturn(output);

        var result = segmentController.createSegment(input);

        assertTrue(result.getStatusCode().is2xxSuccessful());
        assertTrue(result.getBody() instanceof SegmentDTO);
        SegmentDTO body = (SegmentDTO) result.getBody();
        assertEquals(1L, body.getId());
        assertEquals("New Segment", body.getName());
    }

    @Test
    @WithMockUser
    void testUpdateSegment() {
        SegmentDTO input = new SegmentDTO(1L, "Updated Segment");
        SegmentDTO output = new SegmentDTO(1L, "Updated Segment");
        when(segmentService.save(any(SegmentDTO.class))).thenReturn(output);

        var result = segmentController.updateSegment(1L, input);

        assertTrue(result.getStatusCode().is2xxSuccessful());
        assertTrue(result.getBody() instanceof SegmentDTO);
        SegmentDTO body = (SegmentDTO) result.getBody();
        assertEquals("Updated Segment", body.getName());
    }

    @Test
    @WithMockUser
    void testDeleteSegment() {
        doNothing().when(segmentService).deleteById(1L);

        segmentController.deleteSegment(1L);

        verify(segmentService).deleteById(1L);
    }
}