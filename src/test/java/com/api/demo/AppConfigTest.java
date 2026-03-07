package com.api.demo;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class AppConfigTest {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    void testRestTemplateBean() {
        assertNotNull(restTemplate);
    }
}