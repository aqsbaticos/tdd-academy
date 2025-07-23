package com.tddacademy.zoo.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

class EnclosureTest {

    @Test
    @DisplayName("Should create a valid Enclosure with all required fields")
    void shouldCreateValidEnclosure() {
        // Given
        Long id = 1L;
        String name = "Lion Habitat";
        String type = "Savanna";
        Double area = 5000.0;
        String climate = "Tropical";
        List<Animal> animals = new ArrayList<>();

        // When
        Enclosure enclosure = new Enclosure(id, name, type, area, climate, animals);

        // Then
        assertNotNull(enclosure);
        assertEquals(id, enclosure.id());
        assertEquals(name, enclosure.name());
        assertEquals(type, enclosure.type());
        assertEquals(area, enclosure.area());
        assertEquals(climate, enclosure.climate());
        assertEquals(animals, enclosure.animals());
    }

    @Test
    @DisplayName("Should create enclosure with null area")
    void shouldCreateEnclosureWithNullArea() {
        // Given
        Long id = 1L;
        String name = "Lion Habitat";
        String type = "Savanna";
        Double area = null;
        String climate = "Tropical";
        List<Animal> animals = new ArrayList<>();

        // When
        Enclosure enclosure = new Enclosure(id, name, type, area, climate, animals);

        // Then
        assertNotNull(enclosure);
        assertNull(enclosure.area());
    }

    @Test
    @DisplayName("Should throw exception when enclosure name is null")
    void shouldThrowExceptionWhenNameIsNull() {
        // Given
        Long id = 1L;
        String name = null;
        String type = "Savanna";
        Double area = 5000.0;
        String climate = "Tropical";
        List<Animal> animals = new ArrayList<>();

        // When & Then
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Enclosure(id, name, type, area, climate, animals)
        );
        assertEquals("Enclosure name cannot be null or empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when enclosure type is null")
    void shouldThrowExceptionWhenTypeIsNull() {
        // TODO: Complete this test
        // 1. Create test data with type = null
        // 2. Use assertThrows to test that creating an Enclosure with null type throws IllegalArgumentException
        // 3. Verify the exception message is "Enclosure type cannot be null or empty"
        
        // Your code here:
         Long id = 1L;
         String name = "Lion Habitat";
         String type = null;
         Double area = 5000.0;
         String climate = "Tropical";
         List<Animal> animals = new ArrayList<>();

         IllegalArgumentException exception = assertThrows(
             IllegalArgumentException.class,
             () -> new Enclosure(id, name, type, area, climate, animals)
         );
         assertEquals("Enclosure type cannot be null or empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when enclosure area is negative")
    void shouldThrowExceptionWhenAreaIsNegative() {
        // TODO: Complete this test
        // 1. Create test data with area = -100.0
        // 2. Use assertThrows to test that creating an Enclosure with negative area throws IllegalArgumentException
        // 3. Verify the exception message is "Enclosure area must be positive"
        
        // Your code here:
         Long id = 1L;
         String name = "Lion Habitat";
         String type = "Savanna";
         Double area = -100.0;
         String climate = "Tropical";
         List<Animal> animals = new ArrayList<>();

         IllegalArgumentException exception = assertThrows(
             IllegalArgumentException.class,
             () -> new Enclosure(id, name, type, area, climate, animals)
         );
         assertEquals("Enclosure area must be positive", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when enclosure area is zero")
    void shouldThrowExceptionWhenAreaIsZero() {
        // Given
        Long id = 1L;
        String name = "Lion Habitat";
        String type = "Savanna";
        Double area = 0.0;
        String climate = "Tropical";
        List<Animal> animals = new ArrayList<>();

        // When & Then
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Enclosure(id, name, type, area, climate, animals)
        );
        assertEquals("Enclosure area must be positive", exception.getMessage());
    }
} 