package com.equitasit.ms.emp.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import com.equitasit.ms.emp.exception.EmpException;
import com.equitasit.ms.emp.exception.EmpExceptionConstants;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.equitasit.ms.emp.dto.EmpDTO;
import com.equitasit.ms.emp.service.EmpService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmpControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private String baseUrl;

    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void init() {
        baseUrl = "http://localhost:" + port + "/emp";
    }

    @Test
    @Order(1)
    public void testSave() throws Exception {

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        EmpDTO empDTO = getEmpDTO();

        HttpEntity<String> request =
                new HttpEntity<String>(mapper.writeValueAsString(empDTO), headers);

        ResponseEntity<String> responseEntity = this.testRestTemplate.postForEntity(baseUrl, request, String.class);

        Assertions.assertNotNull(responseEntity);

        Assertions.assertEquals(HttpStatus.CREATED.value(), responseEntity.getStatusCodeValue());

        Assertions.assertNotNull(responseEntity.getBody());

        JsonNode tree = mapper.readTree(responseEntity.getBody());

        Assertions.assertEquals(1234, tree.at("/empno").asInt());

    }

    @Test
    @Order(2)
    public void testUpdate() throws Exception {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        EmpDTO empDTO = getEmpDTO();
        empDTO.setEname("siva");

        HttpEntity<String> request =
                new HttpEntity<String>(mapper.writeValueAsString(empDTO), headers);

        ResponseEntity<String> responseEntity = this.testRestTemplate.exchange(baseUrl, HttpMethod.PUT, request, String.class);

        Assertions.assertNotNull(responseEntity);

        Assertions.assertTrue(responseEntity.getStatusCode().is2xxSuccessful());

        Assertions.assertNotNull(responseEntity.getBody());

        JsonNode tree = mapper.readTree(responseEntity.getBody());

        Assertions.assertEquals("siva", tree.at("/ename").asText());

    }

    @Test
    @Order(3)
    public void testGetById() throws Exception {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<String> responseEntity = this.testRestTemplate.getForEntity(baseUrl + "/1234", String.class);

        Assertions.assertNotNull(responseEntity);

        Assertions.assertTrue(responseEntity.getStatusCode().is2xxSuccessful());

        Assertions.assertNotNull(responseEntity.getBody());

        JsonNode tree = mapper.readTree(responseEntity.getBody());

        Assertions.assertEquals(1234, tree.at("/empno").asInt());

    }

    @Test
    @Order(4)
    public void testGetByIdWhenIdNotFound() throws Exception {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<String> responseEntity = this.testRestTemplate.getForEntity(baseUrl + "/1235", String.class);

        Assertions.assertNotNull(responseEntity);

        Assertions.assertTrue(responseEntity.getStatusCode().is4xxClientError());

    }

    @Test
    @Order(5)
    public void testGetAll() throws Exception {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<String> responseEntity = this.testRestTemplate.getForEntity(baseUrl, String.class);

        Assertions.assertNotNull(responseEntity);

        Assertions.assertTrue(responseEntity.getStatusCode().is2xxSuccessful());

        Assertions.assertNotNull(responseEntity.getBody());

        JsonNode tree = mapper.readTree(responseEntity.getBody());

        Assertions.assertEquals(1234, tree.at("/0/empno").asInt());
    }

    @Test
    @Order(6)
    public void testRemove() throws Exception {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);


        HttpEntity<String> request = new HttpEntity<String>(headers);

        ResponseEntity<String> responseEntity = this.testRestTemplate.exchange(baseUrl + "/1234", HttpMethod.DELETE, request, String.class);

        Assertions.assertNotNull(responseEntity);

        Assertions.assertEquals(HttpStatus.NO_CONTENT.value(), responseEntity.getStatusCodeValue());


    }


    private EmpDTO getEmpDTO() {
        EmpDTO empDTO = new EmpDTO();
        empDTO.setEmpno(1234);
        empDTO.setEname("nityaay");
        return empDTO;
    }

}
