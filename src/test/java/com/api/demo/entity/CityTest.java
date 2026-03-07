package com.api.demo.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

class CityTest {

    @Test
    void testNoArgsConstructor() {
        City city = new City();
        assertNotNull(city);
    }

    @Test
    void testAllArgsConstructor() {
        City city = new City(1L, 123, "Test City");
        assertEquals(1L, city.getId());
        assertEquals(123, city.getCode());
        assertEquals("Test City", city.getName());
    }

    @Test
    void testSettersAndGetters() {
        City city = new City();
        city.setId(2L);
        city.setCode(456);
        city.setName("Another City");

        assertEquals(2L, city.getId());
        assertEquals(456, city.getCode());
        assertEquals("Another City", city.getName());
    }

    @Test
    void testEqualsAndHashCode() {
        City city1 = new City(1L, 123, "City");
        City city2 = new City(1L, 123, "City");
        City city3 = new City(2L, 123, "City");

        assertEquals(city1, city2);
        assertNotEquals(city1, city3);
        assertEquals(city1.hashCode(), city2.hashCode());
    }
}