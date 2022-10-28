package com.example.ob03restdatajpa.controller;

import com.example.ob03restdatajpa.entities.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {

    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp(){
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @Test
    void create() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                {
                    "usuario": "TestUsr",
                    "ultimaConexión": "1111-11-11",
                    "ubicaciones": 1,
                    "procesador": "111111111111",
                    "active": false
                }
                """;

        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops", HttpMethod.POST, request, Laptop.class);

        assertEquals(1L, response.getBody().getId());
        assertEquals("TestUsr", response.getBody().getUsuario());
    }

    @Test
    void delete() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                {
                    "id": 1,
                    "usuario": "TestUsr",
                    "ultimaConexión": "1111-11-11",
                    "ubicaciones": 1,
                    "procesador": "111111111111",
                    "active": true
                }
                """;
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops/1", HttpMethod.DELETE, request, Laptop.class);

        ResponseEntity<Laptop> response3 =
                testRestTemplate.getForEntity("/api/laptops/1", Laptop.class);

        assertEquals(HttpStatus.NOT_FOUND, response3.getStatusCode());;
    }

    @Test
    void findAll() {
        ResponseEntity<Laptop[]> response =
                testRestTemplate.getForEntity("/api/laptops", Laptop[].class);

        List<Laptop> laptops = Arrays.asList(response.getBody());
        assertEquals(0, laptops.size());
        assertEquals(200, response.getStatusCodeValue());

    }

    @Test
    void update() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                {
                    "usuario": "TestUsr",
                    "ultimaConexión": "1111-11-11",
                    "ubicaciones": 1,
                    "procesador": "111111111111",
                    "active": false
                }
                """;

        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops", HttpMethod.POST, request, Laptop.class);

        assertEquals(2L, response.getBody().getId());

        HttpHeaders headers2 = new HttpHeaders();
        headers2.setContentType(MediaType.APPLICATION_JSON);
        headers2.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json2 = """
                {
                    "id": 2,
                    "usuario": "TestUsr",
                    "ultimaConexión": "1111-11-11",
                    "ubicaciones": 1,
                    "procesador": "111111111111",
                    "active": true
                }
                """;
        HttpEntity<String> request2 = new HttpEntity<>(json2, headers2);
        ResponseEntity<Laptop> response2 = testRestTemplate.exchange("/api/laptops/2", HttpMethod.PUT, request2, Laptop.class);

        assertEquals(HttpStatus.OK, response2.getStatusCode());

        ResponseEntity<Laptop> response3 =
                testRestTemplate.getForEntity("/api/laptops/2", Laptop.class);

        assertEquals(HttpStatus.OK, response3.getStatusCode());
        assertEquals(true, response3.getBody().getActive());
    }

    @Test
    void findOneById() {

        ResponseEntity<Laptop> response =
                testRestTemplate.getForEntity("/api/laptops/2", Laptop.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("111111111111", response.getBody().getProcesador());
    }

    @Test
    void deleteAll() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json = """
                {
                    "usuario": "TestUsr",
                    "ultimaConexión": "1111-11-11",
                    "ubicaciones": 1,
                    "procesador": "111111111111",
                    "active": false
                }
                """;

        HttpEntity<String> request = new HttpEntity<>(json, headers);
        ResponseEntity<Laptop> response = testRestTemplate.exchange("/api/laptops", HttpMethod.POST, request, Laptop.class);

        assertEquals(3L, response.getBody().getId());

        HttpHeaders headers2 = new HttpHeaders();
        headers2.setContentType(MediaType.APPLICATION_JSON);
        headers2.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String json2 = """
                {
                    "id": 1,
                    "usuario": "TestUsr",
                    "ultimaConexión": "1111-11-11",
                    "ubicaciones": 1,
                    "procesador": "111111111111",
                    "active": true
                }
                """;
        HttpEntity<String> request2 = new HttpEntity<>(json2, headers2);
        ResponseEntity<Laptop> response2 = testRestTemplate.exchange("/api/laptops", HttpMethod.DELETE, request2, Laptop.class);

        ResponseEntity<Laptop> response3 =
                testRestTemplate.getForEntity("/api/laptops/2", Laptop.class);
        ResponseEntity<Laptop> response4 =
                testRestTemplate.getForEntity("/api/laptops/3", Laptop.class);

        assertEquals(HttpStatus.NOT_FOUND, response3.getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND, response4.getStatusCode());;
    }
}