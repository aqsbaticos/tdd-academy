package com.tddacademy.zoo.service;

import com.tddacademy.zoo.model.Animal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TodoExercisesTest {

    @Mock
    private AnimalRepository animalRepository;

    @Spy
    private NotificationService notificationService;

    @InjectMocks
    private AnimalService animalService;

    @InjectMocks
    private ZooManager zooManager;

    private Animal simba;
    private Animal nala;
    private Animal timon;

    @BeforeEach
    void setUp() {
        simba = new Animal("Simba", "Lion", 180.5, LocalDate.of(2020, 5, 15), "Healthy");
        nala = new Animal("Nala", "Lion", 160.0, LocalDate.of(2020, 6, 20), "Healthy");
        timon = new Animal("Timon", "Meerkat", 2.5, LocalDate.of(2021, 3, 10), "Healthy");
        zooManager = new ZooManager(animalService, notificationService);
    }

    // ========== MOCK EXERCISES ==========

    @Test
    @DisplayName("TODO: Mock Exercise 1 - Should find animal by species")
    void shouldFindAnimalBySpecies() {
        // TODO: Complete this test using mocks
        // 1. Mock animalRepository.findBySpecies("Lion") to return a list with simba and nala
        // 2. Call animalService.getAnimalsBySpecies("Lion")
        // 3. Assert that the result contains 2 animals
        // 4. Assert that both animals are lions
        
        // Your code here:
         when(animalRepository.findBySpecies("Lion")).thenReturn(Arrays.asList(simba, nala));

         List<Animal> lions = animalService.getAnimalsBySpecies("Lion");

         assertEquals(2, lions.size());
         assertTrue(lions.stream().allMatch(animal -> "Lion".equals(animal.getSpecies())));
    }

    @Test
    @DisplayName("TODO: Mock Exercise 2 - Should handle animal not found")
    void shouldHandleAnimalNotFound() {
        // TODO: Complete this test using mocks
        // 1. Mock animalRepository.findById(999L) to return Optional.empty()
        // 2. Call animalService.getAnimalById(999L)
        // 3. Assert that the result is empty
        
        // Your code here:
         when(animalRepository.findById(999L)).thenReturn(Optional.empty());

         Optional<Animal> result = animalService.getAnimalById(999L);

         assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("TODO: Mock Exercise 3 - Should verify repository save was called")
    void shouldVerifyRepositorySaveWasCalled() {
        // TODO: Complete this test using mocks
        // 1. Mock animalRepository.save(any(Animal.class)) to return simba with ID 1
        // 2. Call animalService.createAnimal(simba)
        // 3. Verify that animalRepository.save(simba) was called exactly once
        
        // Your code here:
         simba.setId(1L);
         when(animalRepository.save(any(Animal.class))).thenReturn(simba);

         animalService.createAnimal(simba);

         verify(animalRepository, times(1)).save(simba);
    }

    // ========== STUB EXERCISES ==========

    @Test
    @DisplayName("TODO: Stub Exercise 1 - Should calculate average weight with stub data")
    void shouldCalculateAverageWeightWithStubData() {
        // TODO: Complete this test using stubs
        // 1. Create stub data: simba (180.5), nala (160.0), timon (2.5)
        // 2. Mock animalRepository.findAll() to return this stub data
        // 3. Call animalService.getAverageWeight()
        // 4. Assert the average is 114.33 (with 0.01 precision)
        
        // Your code here:
         List<Animal> animals = Arrays.asList(simba, nala, timon);
         when(animalRepository.findAll()).thenReturn(animals);

         double averageWeight = animalService.getAverageWeight();

         assertEquals(114.33, averageWeight, 0.01);
    }

    @Test
    @DisplayName("TODO: Stub Exercise 2 - Should handle empty repository with stub")
    void shouldHandleEmptyRepositoryWithStub() {
        // TODO: Complete this test using stubs
        // 1. Mock animalRepository.findAll() to return empty list
        // 2. Call animalService.getAverageWeight()
        // 3. Assert the result is 0.0
        
        // Your code here:
         when(animalRepository.findAll()).thenReturn(Arrays.asList());

         double averageWeight = animalService.getAverageWeight();

         assertEquals(0.0, averageWeight, 0.01);
    }

    @Test
    @DisplayName("TODO: Stub Exercise 3 - Should get animal count with stub")
    void shouldGetAnimalCountWithStub() {
        // TODO: Complete this test using stubs
        // 1. Mock animalRepository.count() to return 15
        // 2. Call animalService.getAnimalCount()
        // 3. Assert the result is 15
        
        // Your code here:
         when(animalRepository.count()).thenReturn(15);

         int count = animalService.getAnimalCount();

         assertEquals(15, count);
    }

    // ========== SPY EXERCISES ==========

    @Test
    @DisplayName("TODO: Spy Exercise 1 - Should verify email notification for new animal")
    void shouldVerifyEmailNotificationForNewAnimal() {
        // TODO: Complete this test using spies
        // 1. Mock animalRepository.save(any(Animal.class)) to return simba with ID 1
        // 2. Call zooManager.addNewAnimal(simba)
        // 3. Verify that notificationService.sendEmail was called with:
        //    - to: "staff@zoo.com"
        //    - subject: "New Animal Added"
        //    - message containing "Simba"
        
        // Your code here:
         simba.setId(1L);
         when(animalRepository.save(any(Animal.class))).thenReturn(simba);

         zooManager.addNewAnimal(simba);

         verify(notificationService, times(1)).sendEmail(
             eq("staff@zoo.com"),
             eq("New Animal Added"),
             contains("Simba")
         );
    }

    @Test
    @DisplayName("TODO: Spy Exercise 2 - Should verify SMS notification for animal removal")
    void shouldVerifySMSNotificationForAnimalRemoval() {
        // TODO: Complete this test using spies
        // 1. Mock animalRepository.findById(1L) to return simba with ID 1
        // 2. Mock animalRepository.existsById(1L) to return true
        // 3. Mock animalRepository.deleteById(1L) to do nothing
        // 4. Call zooManager.removeAnimal(1L)
        // 5. Verify that notificationService.sendSMS was called with:
        //    - phone: "+1234567890"
        //    - message containing "Simba"
        
        // Your code here:
         simba.setId(1L);
         when(animalRepository.findById(1L)).thenReturn(Optional.of(simba));
         when(animalRepository.existsById(1L)).thenReturn(true);
         doNothing().when(animalRepository).deleteById(1L);

         zooManager.removeAnimal(1L);

         verify(notificationService, times(1)).sendSMS(
             eq("+1234567890"),
             contains("Simba")
         );
    }

    @Test
    @DisplayName("TODO: Spy Exercise 3 - Should verify no notification for healthy animal")
    void shouldVerifyNoNotificationForHealthyAnimal() {
        // TODO: Complete this test using spies
        // 1. Mock animalRepository.findById(1L) to return simba with health status "Healthy"
        // 2. Call zooManager.checkAnimalHealth(1L)
        // 3. Verify that notificationService.sendEmail was NEVER called
        
        // Your code here:
         simba.setId(1L);
         simba.setHealthStatus("Healthy");
         when(animalRepository.findById(1L)).thenReturn(Optional.of(simba));

         zooManager.checkAnimalHealth(1L);

         verify(notificationService, never()).sendEmail(any(), any(), any());
    }

    // ========== ADVANCED EXERCISES ==========

    @Test
    @DisplayName("TODO: Advanced Exercise 1 - Should verify multiple repository calls")
    void shouldVerifyMultipleRepositoryCalls() {
        // TODO: Complete this test using mocks and verification
        // 1. Mock animalRepository.findAll() to return list with simba and nala
        // 2. Call animalService.getAverageWeight()
        // 3. Verify that animalRepository.findAll() was called exactly once
        // 4. Assert the average weight is 170.25
        
        // Your code here:
         List<Animal> animals = Arrays.asList(simba, nala);
         when(animalRepository.findAll()).thenReturn(animals);

         double averageWeight = animalService.getAverageWeight();

         verify(animalRepository, times(1)).findAll();
         assertEquals(170.25, averageWeight, 0.01);
    }

    @Test
    @DisplayName("TODO: Advanced Exercise 2 - Should verify notification parameters exactly")
    void shouldVerifyNotificationParametersExactly() {
        // TODO: Complete this test using spies and exact parameter matching
        // 1. Mock animalRepository.save(any(Animal.class)) to return simba with ID 1
        // 2. Call zooManager.addNewAnimal(simba)
        // 3. Verify that notificationService.sendEmail was called with exact parameters:
        //    - to: "staff@zoo.com"
        //    - subject: "New Animal Added"
        //    - message: "New animal Simba has been added to the zoo."
        
        // Your code here:
         simba.setId(1L);
         when(animalRepository.save(any(Animal.class))).thenReturn(simba);

         zooManager.addNewAnimal(simba);

         verify(notificationService).sendEmail(
             "staff@zoo.com",
             "New Animal Added",
             "New animal Simba has been added to the zoo."
         );
    }

    @Test
    @DisplayName("TODO: Advanced Exercise 3 - Should handle complex scenario with multiple mocks")
    void shouldHandleComplexScenarioWithMultipleMocks() {
        // TODO: Complete this test using multiple mocks and spies
        // 1. Mock animalRepository.findById(1L) to return simba with health status "Sick"
        // 2. Call zooManager.checkAnimalHealth(1L)
        // 3. Verify that notificationService.sendEmail was called with:
        //    - to: "vet@zoo.com"
        //    - subject: "Animal Health Alert"
        //    - message containing "1"
        // 4. Verify that animalRepository.findById(1L) was called exactly once
        
        // Your code here:
         simba.setId(1L);
         simba.setHealthStatus("Sick");
         when(animalRepository.findById(1L)).thenReturn(Optional.of(simba));

         zooManager.checkAnimalHealth(1L);

         verify(notificationService, times(1)).sendEmail(
             eq("vet@zoo.com"),
             eq("Animal Health Alert"),
             contains("1")
         );
         verify(animalRepository, times(1)).findById(1L);
    }
} 