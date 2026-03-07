package com.api.demo.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

class SegmentTest {

    @Test
    void testNoArgsConstructor() {
        Segment segment = new Segment();
        assertNotNull(segment);
    }

    @Test
    void testAllArgsConstructor() {
        Segment segment = new Segment(1L, "Test Segment");
        assertEquals(1L, segment.getId());
        assertEquals("Test Segment", segment.getName());
    }

    @Test
    void testSettersAndGetters() {
        Segment segment = new Segment();
        segment.setId(2L);
        segment.setName("Another Segment");

        assertEquals(2L, segment.getId());
        assertEquals("Another Segment", segment.getName());
    }

    @Test
    void testEqualsAndHashCode() {
        Segment segment1 = new Segment(1L, "Segment");
        Segment segment2 = new Segment(1L, "Segment");
        Segment segment3 = new Segment(2L, "Segment");

        assertEquals(segment1, segment2);
        assertNotEquals(segment1, segment3);
        assertEquals(segment1.hashCode(), segment2.hashCode());
    }
}