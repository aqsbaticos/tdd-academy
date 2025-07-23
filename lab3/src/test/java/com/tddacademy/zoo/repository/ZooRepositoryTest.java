package com.tddacademy.zoo.repository;

import com.tddacademy.zoo.model.Zoo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ZooRepositoryTest {

    @Autowired
    private ZooRepository zooRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Zoo manilaZoo;
    private Zoo cebuSafari;

    @BeforeEach
    void setUp() {
        // Create test data
        manilaZoo = new Zoo("Manila Zoo", "Manila, Philippines", "A beautiful zoo in the heart of Manila");
        cebuSafari = new Zoo("Cebu Safari", "Cebu, Philippines", "World famous safari park");
    }

    @Test
    @DisplayName("Should save a zoo successfully")
    void shouldSaveZooSuccessfully() {
        // When
        Zoo savedZoo = zooRepository.save(manilaZoo);

        // Then
        assertNotNull(savedZoo.getId());
        assertEquals("Manila Zoo", savedZoo.getName());
        assertEquals("Manila, Philippines", savedZoo.getLocation());
    }

    @Test
    @DisplayName("Should find zoo by id")
    void shouldFindZooById() {
        // Given
        Zoo savedZoo = zooRepository.save(manilaZoo);
        Long zooId = savedZoo.getId();

        // When
        Optional<Zoo> foundZoo = zooRepository.findById(zooId);

        // Then
        assertTrue(foundZoo.isPresent());
        assertEquals("Manila Zoo", foundZoo.get().getName());
    }

    @Test
    @DisplayName("Should find all zoos")
    void shouldFindAllZoos() {
        // Given
        zooRepository.save(manilaZoo);
        zooRepository.save(cebuSafari);

        // When
        List<Zoo> allZoos = zooRepository.findAll();

        // Then
        assertEquals(2, allZoos.size());
        assertTrue(allZoos.stream().anyMatch(zoo -> zoo.getName().equals("Manila Zoo")));
        assertTrue(allZoos.stream().anyMatch(zoo -> zoo.getName().equals("Cebu Safari")));
    }

    @Test
    @DisplayName("Should find zoos by name containing")
    void shouldFindZoosByNameContaining() {
        // TODO: Complete this test
        // 1. Save both manilaZoo and cebuSafari to the repository
        // 2. Use zooRepository.findByNameContainingIgnoreCase("Manila") to search
        // 3. Assert that the result contains only the Manila Zoo
        // 4. Assert that the result size is 1
        
        // Your code here:
         zooRepository.save(manilaZoo);
         zooRepository.save(cebuSafari);

         List<Zoo> manilaZoos = zooRepository.findByNameContainingIgnoreCase("Manila");

         assertEquals(1, manilaZoos.size());
         assertEquals("Manila Zoo", manilaZoos.get(0).getName());
    }

    @Test
    @DisplayName("Should find zoos by location containing")
    void shouldFindZoosByLocationContaining() {
        // TODO: Complete this test
        // 1. Save both manilaZoo and cebuSafari to the repository
        // 2. Use zooRepository.findByLocationContainingIgnoreCase("Philippines") to search
        // 3. Assert that the result contains both zoos
        // 4. Assert that the result size is 2
        
        // Your code here:
         zooRepository.save(manilaZoo);
         zooRepository.save(cebuSafari);

         List<Zoo> philippineZoos = zooRepository.findByLocationContainingIgnoreCase("Philippines");

         assertEquals(2, philippineZoos.size());
         assertTrue(philippineZoos.stream().anyMatch(zoo -> zoo.getName().equals("Manila Zoo")));
         assertTrue(philippineZoos.stream().anyMatch(zoo -> zoo.getName().equals("Cebu Safari")));
    }

    @Test
    @DisplayName("Should check if zoo exists by id")
    void shouldCheckIfZooExistsById() {
        // TODO: Complete this test
        // 1. Save manilaZoo to the repository
        // 2. Get the saved zoo's ID
        // 3. Use zooRepository.existsById(savedId) to check existence
        // 4. Assert that the zoo exists
        // 5. Use zooRepository.existsById(999L) to check non-existence
        // 6. Assert that the zoo does not exist
        
        // Your code here:
         Zoo savedZoo = zooRepository.save(manilaZoo);
         Long savedId = savedZoo.getId();

         assertTrue(zooRepository.existsById(savedId));
         assertFalse(zooRepository.existsById(999L));
    }

    @Test
    @DisplayName("Should delete zoo by id")
    void shouldDeleteZooById() {
        // TODO: Complete this test
        // 1. Save manilaZoo to the repository
        // 2. Get the saved zoo's ID
        // 3. Use zooRepository.deleteById(savedId) to delete the zoo
        // 4. Use zooRepository.findById(savedId) to try to find the deleted zoo
        // 5. Assert that the zoo is not found (Optional.empty())
        
        // Your code here:
         Zoo savedZoo = zooRepository.save(manilaZoo);
         Long savedId = savedZoo.getId();

         zooRepository.deleteById(savedId);
         Optional<Zoo> deletedZoo = zooRepository.findById(savedId);

         assertTrue(deletedZoo.isEmpty());
    }
} 