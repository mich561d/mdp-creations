package facades;

import utils.EMF_Creator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.DataManager;

public class CourseFacadeTest {

    private static EntityManagerFactory emf;
    private static Facade facade;

    public CourseFacadeTest() {
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
    public void testGetAllCoursesMustGiveSizeOfThree() {
        // Arrange
        int expected = 3;
        // Act
        int result = facade.getAllCourses().size();
        // Assert
        assertEquals(expected, result, "Expects four rows in the database but only retrieving three since one is disabled");
    }
}
