package utils;

import entities.Course;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class DataManager {

    public static void main(String[] args) {
        // Setup start data
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Course.deleteAll").executeUpdate();
            em.persist(new Course("Test", "TST", "https://datsoftlyngby.github.io/soft2020fall/TST/"));
            em.persist(new Course("System Integration", "SI", "https://datsoftlyngby.github.io/soft2020fall/SI/"));
            em.persist(new Course("Large Systems Development", "LSD", "https://datsoftlyngby.github.io/soft2020fall/LSD/"));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public static void setupTestData(EntityManager em) {
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Course.deleteAll").executeUpdate();
            em.persist(new Course("Test", "TST", "https://datsoftlyngby.github.io/soft2020fall/TST/"));
            em.persist(new Course("System Integration", "SI", "https://datsoftlyngby.github.io/soft2020fall/SI/"));
            em.persist(new Course("Large Systems Development", "LSD", "https://datsoftlyngby.github.io/soft2020fall/LSD/"));
            em.persist(new Course("Discrete Mathematics", "DM", "https://datsoftlyngby.github.io/soft2020fall/DM/", false));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public static void tearDownTestData(EntityManager em) {
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Course.deleteAll").executeUpdate();
            em.createNativeQuery("ALTER TABLE `COURSE` AUTO_INCREMENT = 1").executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
