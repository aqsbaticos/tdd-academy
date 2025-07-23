package com.tddacademy.zoo.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

class AnimalTest {

    @Test
    @DisplayName("Should create a valid Animal with all required fields")
    void shouldCreateValidAnimal() {
        // Given
        Long id = 1L;
        String name = "Simba";
        String species = "Lion";
        String breed = "African Lion";
        LocalDate dateOfBirth = LocalDate.of(2020, 5, 15);
        Double weight = 180.5;
        String healthStatus = "Healthy";

        // When
        Animal animal = new Animal(id, name, species, breed, dateOfBirth, weight, healthStatus);

        // Then
        assertNotNull(animal);
        assertEquals(id, animal.id());
        assertEquals(name, animal.name());
        assertEquals(species, animal.species());
        assertEquals(breed, animal.breed());
        assertEquals(dateOfBirth, animal.dateOfBirth());
        assertEquals(weight, animal.weight());
        assertEquals(healthStatus, animal.healthStatus());
    }

    @Test
    @DisplayName("Should create animal with null weight")
    void shouldCreateAnimalWithNullWeight() {
        // Given
        Long id = 1L;
        String name = "Simba";
        String species = "Lion";
        String breed = "African Lion";
        LocalDate dateOfBirth = LocalDate.of(2020, 5, 15);
        Double weight = null;
        String healthStatus = "Healthy";

        // When
        Animal animal = new Animal(id, name, species, breed, dateOfBirth, weight, healthStatus);

        // Then
        assertNotNull(animal);
        assertNull(animal.weight());
    }

    @Test
    @DisplayName("Should throw exception when animal name is null")
    void shouldThrowExceptionWhenNameIsNull() {
        // TODO: Complete this test
        // 1. Create test data with name = null
        // 2. Use assertThrows to test that creating an Animal with null name throws IllegalArgumentException
        // 3. Verify the exception message is "Animal name cannot be null or empty"
        
        // Your code here:
         Long id = 1L;
         String name = null;
         String species = "Lion";
         String breed = "African Lion";
         LocalDate dateOfBirth = LocalDate.of(2020, 5, 15);
         Double weight = 180.5;
         String healthStatus = "Healthy";

         IllegalArgumentException exception = assertThrows(
             IllegalArgumentException.class,
             () -> new Animal(id, name, species, breed, dateOfBirth, weight, healthStatus)
         );
         assertEquals("Animal name cannot be null or empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when animal species is null")
    void shouldThrowExceptionWhenSpeciesIsNull() {
        // TODO: Complete this test
        // 1. Create test data with species = null
        // 2. Use assertThrows to test that creating an Animal with null species throws IllegalArgumentException
        // 3. Verify the exception message is "Animal species cannot be null or empty"
        
        // Your code here:
         Long id = 1L;
         String name = "Simba";
         String species = null;
         String breed = "African Lion";
         LocalDate dateOfBirth = LocalDate.of(2020, 5, 15);
         Double weight = 180.5;
         String healthStatus = "Healthy";

         IllegalArgumentException exception = assertThrows(
             IllegalArgumentException.class,
             () -> new Animal(id, name, species, breed, dateOfBirth, weight, healthStatus)
         );
         assertEquals("Animal species cannot be null or empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when animal weight is negative")
    void shouldThrowExceptionWhenWeightIsNegative() {
        // Given
        Long id = 1L;
        String name = "Simba";
        String species = "Lion";
        String breed = "African Lion";
        LocalDate dateOfBirth = LocalDate.of(2020, 5, 15);
        Double weight = -50.0;
        String healthStatus = "Healthy";

        // When & Then
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Animal(id, name, species, breed, dateOfBirth, weight, healthStatus)
        );
        assertEquals("Animal weight must be positive", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when animal weight is zero")
    void shouldThrowExceptionWhenWeightIsZero() {
        // TODO: Complete this test
        // 1. Create test data with weight = 0.0
        // 2. Use assertThrows to test that creating an Animal with zero weight throws IllegalArgumentException
        // 3. Verify the exception message is "Animal weight must be positive"
        
        // Your code here:
         Long id = 1L;
         String name = "Simba";
         String species = "Lion";
         String breed = "African Lion";
         LocalDate dateOfBirth = LocalDate.of(2020, 5, 15);
         Double weight = 0.0;
         String healthStatus = "Healthy";

         IllegalArgumentException exception = assertThrows(
             IllegalArgumentException.class,
             () -> new Animal(id, name, species, breed, dateOfBirth, weight, healthStatus)
         );
         assertEquals("Animal weight must be positive", exception.getMessage());
    }
} 