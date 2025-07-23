package com.tddacademy.zoo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tddacademy.zoo.model.Zoo;
import com.tddacademy.zoo.service.ZooService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ZooController.class)
class ZooControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ZooService zooService;

    @Autowired
    private ObjectMapper objectMapper;

    private Zoo testZoo;
    private Zoo createdZoo;

    @BeforeEach
    void setUp() {
        testZoo = new Zoo(null, "Manila Zoo", "Manila, Philippines", 
                         "A beautiful zoo in the heart of Manila", new ArrayList<>(), new ArrayList<>());
        createdZoo = new Zoo(1L, "Manila Zoo", "Manila, Philippines", 
                           "A beautiful zoo in the heart of Manila", new ArrayList<>(), new ArrayList<>());
    }

    @Test
    @DisplayName("GET /api/zoos should return all zoos")
    void shouldReturnAllZoos() throws Exception {
        // Given
        List<Zoo> zoos = Arrays.asList(createdZoo);
        when(zooService.getAllZoos()).thenReturn(zoos);

        // When & Then
        mockMvc.perform(get("/api/zoos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Manila Zoo"))
                .andExpect(jsonPath("$[0].location").value("Manila, Philippines"))
                .andExpect(jsonPath("$[0].description").value("A beautiful zoo in the heart of Manila"));
    }

    @Test
    @DisplayName("GET /api/zoos should return empty list when no zoos exist")
    void shouldReturnEmptyListWhenNoZoosExist() throws Exception {
        // Given
        when(zooService.getAllZoos()).thenReturn(new ArrayList<>());

        // When & Then
        mockMvc.perform(get("/api/zoos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @DisplayName("GET /api/zoos/{id} should return zoo when it exists")
    void shouldReturnZooWhenItExists() throws Exception {
        // TODO: Complete this test
        // 1. Set up the mock: when(zooService.getZooById(1L)).thenReturn(createdZoo);
        // 2. Use mockMvc.perform(get("/api/zoos/1")) to make a GET request
        // 3. Add expectations for:
        //    - status().isOk()
        //    - content().contentType(MediaType.APPLICATION_JSON)
        //    - jsonPath("$.id").value(1)
        //    - jsonPath("$.name").value("Manila Zoo")
        //    - jsonPath("$.location").value("Manila, Philippines")
        //    - jsonPath("$.description").value("A beautiful zoo in the heart of Manila")
        
        // Your code here:
         when(zooService.getZooById(1L)).thenReturn(createdZoo);
         mockMvc.perform(get("/api/zoos/1"))
             .andExpect(status().isOk())
             .andExpect(content().contentType(MediaType.APPLICATION_JSON))
             .andExpect(jsonPath("$.id").value(1))
             .andExpect(jsonPath("$.name").value("Manila Zoo"))
             .andExpect(jsonPath("$.location").value("Manila, Philippines"))
             .andExpect(jsonPath("$.description").value("A beautiful zoo in the heart of Manila"));
    }

    @Test
    @DisplayName("GET /api/zoos/{id} should return 404 when zoo does not exist")
    void shouldReturn404WhenZooDoesNotExist() throws Exception {
        // Given
        when(zooService.getZooById(999L)).thenThrow(new IllegalArgumentException("Zoo not found with id: 999"));

        // When & Then
        mockMvc.perform(get("/api/zoos/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST /api/zoos should create zoo successfully")
    void shouldCreateZooSuccessfully() throws Exception {
        // Given
        when(zooService.createZoo(any(Zoo.class))).thenReturn(createdZoo);

        // When & Then
        mockMvc.perform(post("/api/zoos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testZoo)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Manila Zoo"))
                .andExpect(jsonPath("$.location").value("Manila, Philippines"))
                .andExpect(jsonPath("$.description").value("A beautiful zoo in the heart of Manila"));
    }

    @Test
    @DisplayName("PUT /api/zoos/{id} should update zoo successfully")
    void shouldUpdateZooSuccessfully() throws Exception {
        // TODO: Complete this test
        // 1. Create an updated zoo: new Zoo(1L, "Updated Zoo Name", "Updated Location", "Updated description", new ArrayList<>(), new ArrayList<>())
        // 2. Set up the mock: when(zooService.updateZoo(eq(1L), any(Zoo.class))).thenReturn(updatedZoo);
        // 3. Use mockMvc.perform(put("/api/zoos/1").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(testZoo)))
        // 4. Add expectations for:
        //    - status().isOk()
        //    - content().contentType(MediaType.APPLICATION_JSON)
        //    - jsonPath("$.id").value(1)
        //    - jsonPath("$.name").value("Updated Zoo Name")
        //    - jsonPath("$.location").value("Updated Location")
        //    - jsonPath("$.description").value("Updated description")
        
        // Your code here:
         Zoo updatedZoo = new Zoo(1L, "Updated Zoo Name", "Updated Location", "Updated description", new ArrayList<>(), new ArrayList<>());
         when(zooService.updateZoo(eq(1L), any(Zoo.class))).thenReturn(updatedZoo);
         mockMvc.perform(put("/api/zoos/1")
                 .contentType(MediaType.APPLICATION_JSON)
                 .content(objectMapper.writeValueAsString(testZoo)))
             .andExpect(status().isOk())
             .andExpect(content().contentType(MediaType.APPLICATION_JSON))
             .andExpect(jsonPath("$.id").value(1))
             .andExpect(jsonPath("$.name").value("Updated Zoo Name"))
             .andExpect(jsonPath("$.location").value("Updated Location"))
             .andExpect(jsonPath("$.description").value("Updated description"));
    }

    @Test
    @DisplayName("PUT /api/zoos/{id} should return 404 when zoo does not exist")
    void shouldReturn404WhenUpdatingNonExistentZoo() throws Exception {
        // TODO: Complete this test
        // 1. Set up the mock to throw an exception: when(zooService.updateZoo(eq(999L), any(Zoo.class))).thenThrow(new IllegalArgumentException("Zoo not found with id: 999"));
        // 2. Use mockMvc.perform(put("/api/zoos/999").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(testZoo)))
        // 3. Add expectation for status().isNotFound()
        
        // Your code here:
         when(zooService.updateZoo(eq(999L), any(Zoo.class))).thenThrow(new IllegalArgumentException("Zoo not found with id: 999"));
         mockMvc.perform(put("/api/zoos/999")
                 .contentType(MediaType.APPLICATION_JSON)
                 .content(objectMapper.writeValueAsString(testZoo)))
             .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("DELETE /api/zoos/{id} should delete zoo successfully")
    void shouldDeleteZooSuccessfully() throws Exception {
        // TODO: Complete this test
        // 1. Set up the mock: doNothing().when(zooService).deleteZoo(1L);
        // 2. Use mockMvc.perform(delete("/api/zoos/1")) to make a DELETE request
        // 3. Add expectation for status().isNoContent()
        
        // Your code here:
         doNothing().when(zooService).deleteZoo(1L);
         mockMvc.perform(delete("/api/zoos/1"))
             .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("DELETE /api/zoos/{id} should return 404 when zoo does not exist")
    void shouldReturn404WhenDeletingNonExistentZoo() throws Exception {
        // TODO: Complete this test
        // 1. Set up the mock to throw an exception: doThrow(new IllegalArgumentException("Zoo not found with id: 999")).when(zooService).deleteZoo(999L);
        // 2. Use mockMvc.perform(delete("/api/zoos/999")) to make a DELETE request
        // 3. Add expectation for status().isNotFound()
        
        // Your code here:
         doThrow(new IllegalArgumentException("Zoo not found with id: 999")).when(zooService).deleteZoo(999L);
         mockMvc.perform(delete("/api/zoos/999"))
             .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Should handle malformed JSON in POST request")
    void shouldHandleMalformedJsonInPostRequest() throws Exception {
        // When & Then
        mockMvc.perform(post("/api/zoos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ invalid json }"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Should handle malformed JSON in PUT request")
    void shouldHandleMalformedJsonInPutRequest() throws Exception {
        // TODO: Complete this test
        // 1. Use mockMvc.perform(put("/api/zoos/1").contentType(MediaType.APPLICATION_JSON).content("{ invalid json }"))
        // 2. Add expectation for status().isBadRequest()
        
        // Your code here:
         mockMvc.perform(put("/api/zoos/1")
                 .contentType(MediaType.APPLICATION_JSON)
                 .content("{ invalid json }"))
             .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Should return proper content type for all responses")
    void shouldReturnProperContentTypeForAllResponses() throws Exception {
        // Given
        when(zooService.getAllZoos()).thenReturn(Arrays.asList(createdZoo));
        when(zooService.getZooById(1L)).thenReturn(createdZoo);
        when(zooService.createZoo(any(Zoo.class))).thenReturn(createdZoo);
        when(zooService.updateZoo(eq(1L), any(Zoo.class))).thenReturn(createdZoo);
        doNothing().when(zooService).deleteZoo(1L);

        // When & Then
        mockMvc.perform(get("/api/zoos"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        mockMvc.perform(get("/api/zoos/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        mockMvc.perform(post("/api/zoos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testZoo)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        mockMvc.perform(put("/api/zoos/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testZoo)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        mockMvc.perform(delete("/api/zoos/1"))
                .andExpect(status().isNoContent());
    }
} 