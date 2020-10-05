package facades;

import DTOs.LearningObjectiveDTO;
import DTOs.LearningObjectivesDTO;
import exceptions.LearningObjectiveInvalidInputException;
import exceptions.LearningObjectiveNotFoundException;
import utils.EMF_Creator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.DataManager;

public class LearningObjectiveFacadeTest {

    private static EntityManagerFactory emf;
    private static Facade facade;

    public LearningObjectiveFacadeTest() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = Facade.getFacadeExample(emf);
    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        DataManager.setupTestData(em);
    }

    @AfterEach
    public void tearDown() {
        EntityManager em = emf.createEntityManager();
        DataManager.tearDownTestData(em);
    }

    @Test
    public void testGetLearningObjectiveByIdMustGiveResult() throws LearningObjectiveNotFoundException {
        // Arrange
        int id = 1;
        // Act
        LearningObjectiveDTO result = facade.getLearningObjectiveById(id);
        // Assert
        assertNotNull(result);
    }

    @Test
    public void testGetLearningObjectiveByIdMustGiveNotFound() {
        // Assert
        assertThrows(LearningObjectiveNotFoundException.class, () -> {
            // Arrange
            int id = 9000;
            // Act
            facade.getLearningObjectiveById(id);
        });
    }

    @Test
    public void testGetLearningObjectiveByRandomMustGiveResult() {
        // Arrange
        // Act
        LearningObjectiveDTO result = facade.getLearningObjectiveByRandom();
        // Assert
        assertNotNull(result);
    }

    @Test
    public void testGetAllLearningObjectivesMustGiveSizeOfFive() {
        // Arrange
        int expected = 5;
        // Act
        LearningObjectivesDTO result = facade.getAllLearningObjectives();
        // Assert
        assertEquals(expected, result.getLearningObjectiveDTOs().size());
    }

    @Test
    public void testGetAllLearningObjectivesByCourseIdMustGiveSizeOfOneOrTwo() {
        // Arrange
        int courseId = 1;
        // Act
        LearningObjectivesDTO result = facade.getAllLearningObjectivesByCourseId(courseId);
        // Assert
        assertTrue(0 < result.getLearningObjectiveDTOs().size() && result.getLearningObjectiveDTOs().size() < 3);
    }

    @Test
    public void testGetAllLearningObjectivesByCourseIdMustGiveSizeOfZero() {
        // Arrange
        int courseId = 9000;
        int expected = 0;
        // Act
        LearningObjectivesDTO result = facade.getAllLearningObjectivesByCourseId(courseId);
        // Assert
        assertEquals(expected, result.getLearningObjectiveDTOs().size());
    }

    @Test
    public void testGetAllLearningObjectivesByCourseShortNameMustGiveSizeOfTwo() throws LearningObjectiveInvalidInputException {
        // Arrange
        String shortName = "TST";
        int expected = 2;
        // Act
        LearningObjectivesDTO result = facade.getAllLearningObjectivesByCourseShortName(shortName);
        // Assert
        assertEquals(expected, result.getLearningObjectiveDTOs().size());
    }

    @Test
    public void testGetAllLearningObjectivesByCourseShortNameMustGiveSizeOfZero() throws LearningObjectiveInvalidInputException {
        // Arrange
        String shortName = "RANDOM";
        int expected = 0;
        // Act
        LearningObjectivesDTO result = facade.getAllLearningObjectivesByCourseShortName(shortName);
        // Assert
        assertEquals(expected, result.getLearningObjectiveDTOs().size());
    }

    @Test
    public void testGetAllLearningObjectivesByCourseShortNameMustGiveInvalidInput() {
        // Assert
        assertThrows(LearningObjectiveInvalidInputException.class, () -> {
            // Arrange
            String shortName = null;
            // Act
            facade.getAllLearningObjectivesByCourseShortName(shortName);
        });
    }
}
