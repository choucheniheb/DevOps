package tn.esprit.foyer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.repository.FoyerRepository;
import tn.esprit.tpfoyer.service.FoyerServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

    public class FoyerServiceImplMockTest {

        @Mock
        FoyerRepository foyerRepository;  // Création d'un mock pour FoyerRepository

        @InjectMocks
        FoyerServiceImpl foyerService;  // Injection du mock dans FoyerServiceImpl

        Foyer foyer1, foyer2;

        @BeforeEach
        public void setUp() {
            MockitoAnnotations.openMocks(this);  // Initialisation des mocks

            // Initialisation des objets Foyer pour les tests
            foyer1 = new Foyer(1L, "Foyer 1", 100, null, null);
            foyer2 = new Foyer(2L, "Foyer 2", 150, null, null);
        }

        // Test pour vérifier si tous les foyers sont correctement retournés
        @Test
        public void testRetrieveAllFoyers() {
            // Configurer le comportement du mock
            when(foyerRepository.findAll()).thenReturn(Arrays.asList(foyer1, foyer2));

            // Appeler la méthode à tester
            List<Foyer> foyers = foyerService.retrieveAllFoyers();

            // Vérifier les résultats
            assertNotNull(foyers);
            assertEquals(2, foyers.size());
            assertEquals("Foyer 1", foyers.get(0).getNomFoyer());

            // Vérifier que le mock a bien appelé la méthode findAll()
            verify(foyerRepository, times(1)).findAll();
        }

        // Test pour vérifier la récupération d'un foyer par ID
        @Test
        public void testRetrieveFoyer() {
            // Configurer le comportement du mock pour un foyer particulier
            when(foyerRepository.findById(1L)).thenReturn(Optional.of(foyer1));

            // Appeler la méthode à tester
            Foyer foyer = foyerService.retrieveFoyer(1L);

            // Vérifier les résultats
            assertNotNull(foyer);
            assertEquals(1L, foyer.getIdFoyer());
            assertEquals("Foyer 1", foyer.getNomFoyer());

            // Vérifier que le mock a bien appelé la méthode findById() avec le bon paramètre
            verify(foyerRepository, times(1)).findById(1L);
        }

        // Test pour vérifier l'ajout d'un foyer
        @Test
        public void testAddFoyer() {
            // Configurer le comportement du mock pour l'ajout
            when(foyerRepository.save(foyer1)).thenReturn(foyer1);

            // Appeler la méthode à tester
            Foyer savedFoyer = foyerService.addFoyer(foyer1);

            // Vérifier les résultats
            assertNotNull(savedFoyer);
            assertEquals(foyer1.getIdFoyer(), savedFoyer.getIdFoyer());

            // Vérifier que le mock a bien appelé la méthode save()
            verify(foyerRepository, times(1)).save(foyer1);
        }

        // Test pour vérifier la modification d'un foyer
        @Test
        public void testModifyFoyer() {
            // Configurer le comportement du mock pour la modification
            when(foyerRepository.save(foyer1)).thenReturn(foyer1);

            // Appeler la méthode à tester
            Foyer updatedFoyer = foyerService.modifyFoyer(foyer1);

            // Vérifier les résultats
            assertNotNull(updatedFoyer);
            assertEquals("Foyer 1", updatedFoyer.getNomFoyer());

            // Vérifier que le mock a bien appelé la méthode save()
            verify(foyerRepository, times(1)).save(foyer1);
        }

        // Test pour vérifier la suppression d'un foyer
        @Test
        public void testRemoveFoyer() {
            // Appeler la méthode à tester
            foyerService.removeFoyer(1L);

            // Vérifier que le mock a bien appelé la méthode deleteById() avec le bon paramètre
            verify(foyerRepository, times(1)).deleteById(1L);
        }
    }



