package com.api.demo.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

import com.api.demo.entity.Segment;

class SegmentDTOTest {

    @Test
    void testNoArgsConstructor() {
        SegmentDTO dto = new SegmentDTO();
        assertNotNull(dto);
    }

    @Test
    void testAllArgsConstructor() {
        SegmentDTO dto = new SegmentDTO(1L, "Segment");
        assertEquals(1L, dto.getId());
        assertEquals("Segment", dto.getName());
    }

    @Test
    void testConstructorFromEntity() {
        Segment segment = new Segment(1L, "Segment");
        SegmentDTO dto = new SegmentDTO(segment);
        assertEquals(1L, dto.getId());
        assertEquals("Segment", dto.getName());
    }

    @Test
    void testSettersAndGetters() {
        SegmentDTO dto = new SegmentDTO();
        dto.setId(2L);
        dto.setName("Another Segment");

        assertEquals(2L, dto.getId());
        assertEquals("Another Segment", dto.getName());
    }
}