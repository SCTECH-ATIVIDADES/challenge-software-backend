package com.api.demo.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

class IbgeMunicipioDTOTest {

    @Test
    void testDefaultConstructor() {
        IbgeMunicipioDTO dto = new IbgeMunicipioDTO();
        assertNotNull(dto);
    }

    @Test
    void testSettersAndGetters() {
        IbgeMunicipioDTO dto = new IbgeMunicipioDTO();
        dto.setId(123);
        dto.setNome("Test Municipio");

        assertEquals(123, dto.getId());
        assertEquals("Test Municipio", dto.getNome());
    }
}