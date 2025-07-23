package com.tddacademy.zoo.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

class PersonTest {

    @Test
    @DisplayName("Should create a valid Person with all required fields")
    void shouldCreateValidPerson() {
        // TODO: Complete this test
        // 1. Create test data with all required fields
        // 2. Create a Person object with the test data
        // 3. Assert that the person is not null
        // 4. Assert that all fields match the expected values
        
        // Your code here:
         Long id = 1L;
         String firstName = "John";
         String lastName = "Doe";
         String role = "Zookeeper";
         String email = "john.doe@zoo.com";
         LocalDate hireDate = LocalDate.of(2023, 1, 15);
         Double salary = 45000.0;

         Person person = new Person(id, firstName, lastName, role, email, hireDate, salary);

         assertNotNull(person);
         assertEquals(id, person.id());
         assertEquals(firstName, person.firstName());
         assertEquals(lastName, person.lastName());
         assertEquals(role, person.role());
         assertEquals(email, person.email());
         assertEquals(hireDate, person.hireDate());
         assertEquals(salary, person.salary());
    }

    @Test
    @DisplayName("Should create person with null salary")
    void shouldCreatePersonWithNullSalary() {
        // Given
        Long id = 1L;
        String firstName = "John";
        String lastName = "Doe";
        String role = "Zookeeper";
        String email = "john.doe@zoo.com";
        LocalDate hireDate = LocalDate.of(2023, 1, 15);
        Double salary = null;

        // When
        Person person = new Person(id, firstName, lastName, role, email, hireDate, salary);

        // Then
        assertNotNull(person);
        assertNull(person.salary());
    }

    @Test
    @DisplayName("Should throw exception when person first name is null")
    void shouldThrowExceptionWhenFirstNameIsNull() {
        // TODO: Complete this test
        // 1. Create test data with firstName = null
        // 2. Use assertThrows to test that creating a Person with null firstName throws IllegalArgumentException
        // 3. Verify the exception message is "Person first name cannot be null or empty"
        
        // Your code here:
         Long id = 1L;
         String firstName = null;
         String lastName = "Doe";
         String role = "Zookeeper";
         String email = "john.doe@zoo.com";
         LocalDate hireDate = LocalDate.of(2023, 1, 15);
         Double salary = 45000.0;

         IllegalArgumentException exception = assertThrows(
             IllegalArgumentException.class,
             () -> new Person(id, firstName, lastName, role, email, hireDate, salary)
         );
         assertEquals("Person first name cannot be null or empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when person last name is null")
    void shouldThrowExceptionWhenLastNameIsNull() {
        // Given
        Long id = 1L;
        String firstName = "John";
        String lastName = null;
        String role = "Zookeeper";
        String email = "john.doe@zoo.com";
        LocalDate hireDate = LocalDate.of(2023, 1, 15);
        Double salary = 45000.0;

        // When & Then
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Person(id, firstName, lastName, role, email, hireDate, salary)
        );
        assertEquals("Person last name cannot be null or empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when person role is null")
    void shouldThrowExceptionWhenRoleIsNull() {
        // TODO: Complete this test
        // 1. Create test data with role = null
        // 2. Use assertThrows to test that creating a Person with null role throws IllegalArgumentException
        // 3. Verify the exception message is "Person role cannot be null or empty"
        
        // Your code here:
         Long id = 1L;
         String firstName = "John";
         String lastName = "Doe";
         String role = null;
         String email = "john.doe@zoo.com";
         LocalDate hireDate = LocalDate.of(2023, 1, 15);
         Double salary = 45000.0;

         IllegalArgumentException exception = assertThrows(
             IllegalArgumentException.class,
             () -> new Person(id, firstName, lastName, role, email, hireDate, salary)
         );
         assertEquals("Person role cannot be null or empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when person email is null")
    void shouldThrowExceptionWhenEmailIsNull() {
        // Given
        Long id = 1L;
        String firstName = "John";
        String lastName = "Doe";
        String role = "Zookeeper";
        String email = null;
        LocalDate hireDate = LocalDate.of(2023, 1, 15);
        Double salary = 45000.0;

        // When & Then
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Person(id, firstName, lastName, role, email, hireDate, salary)
        );
        assertEquals("Person email cannot be null or empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when person salary is negative")
    void shouldThrowExceptionWhenSalaryIsNegative() {
        // Given
        Long id = 1L;
        String firstName = "John";
        String lastName = "Doe";
        String role = "Zookeeper";
        String email = "john.doe@zoo.com";
        LocalDate hireDate = LocalDate.of(2023, 1, 15);
        Double salary = -1000.0;

        // When & Then
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Person(id, firstName, lastName, role, email, hireDate, salary)
        );
        assertEquals("Person salary must be positive", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when person salary is zero")
    void shouldThrowExceptionWhenSalaryIsZero() {
        // TODO: Complete this test
        // 1. Create test data with salary = 0.0
        // 2. Use assertThrows to test that creating a Person with zero salary throws IllegalArgumentException
        // 3. Verify the exception message is "Person salary must be positive"
        
        // Your code here:
         Long id = 1L;
         String firstName = "John";
         String lastName = "Doe";
         String role = "Zookeeper";
         String email = "john.doe@zoo.com";
         LocalDate hireDate = LocalDate.of(2023, 1, 15);
         Double salary = 0.0;

         IllegalArgumentException exception = assertThrows(
             IllegalArgumentException.class,
             () -> new Person(id, firstName, lastName, role, email, hireDate, salary)
         );
         assertEquals("Person salary must be positive", exception.getMessage());
    }
} 