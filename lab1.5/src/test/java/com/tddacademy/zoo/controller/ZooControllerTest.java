package com.tddacademy.zoo.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ZooControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /api/zoos should return all zoos")
    void shouldReturnAllZoos() throws Exception {
        // When & Then
        mockMvc.perform(get("/api/zoos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Manila Zoo"))
                .andExpect(jsonPath("$[0].location").value("Manila, Philippines"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Cebu Safari"))
                .andExpect(jsonPath("$[1].location").value("Cebu, Philippines"));
    }

    @Test
    @DisplayName("GET /api/zoos/{id} should return zoo when it exists")
    void shouldReturnZooWhenItExists() throws Exception {
        // TODO: Complete this test
        // 1. Use mockMvc.perform(get("/api/zoos/1")) to make a GET request
        // 2. Add expectations for:
        //    - status().isOk()
        //    - content().contentType(MediaType.APPLICATION_JSON)
        //    - jsonPath("$.id").value(1)
        //    - jsonPath("$.name").value("Manila Zoo")
        //    - jsonPath("$.location").value("Manila, Philippines")
        //    - jsonPath("$.description").value("A beautiful zoo in the heart of Manila")
        
        // Your code here:
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
        // TODO: Complete this test
        // 1. Use mockMvc.perform(get("/api/zoos/999")) to make a GET request for non-existent zoo
        // 2. Add expectation for status().isNotFound()
        
        // Your code here:
         mockMvc.perform(get("/api/zoos/999"))
             .andExpect(status().isNotFound());
    }
} 