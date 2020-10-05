package utils;

import entities.Course;
import entities.LearningObjective;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class DataManager {

    public static void main(String[] args) {
        // Setup start data
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        tearDownTestData(emf.createEntityManager());
        try {
            em.getTransaction().begin();
            Course c1 = new Course("Test", "TST", "https://datsoftlyngby.github.io/soft2020fall/TST/");
            Course c2 = new Course("System Integration", "SI", "https://datsoftlyngby.github.io/soft2020fall/SI/");
            Course c3 = new Course("Large Systems Development", "LSD", "https://datsoftlyngby.github.io/soft2020fall/LSD/");
            LearningObjective lo1 = new LearningObjective(QuestionHolder.TST101Q, null);
            lo1.setCourse(c1);
            LearningObjective lo2 = new LearningObjective(QuestionHolder.TST102Q, null);
            lo2.setCourse(c1);
            LearningObjective lo3 = new LearningObjective(QuestionHolder.TST103Q, null);
            lo3.setCourse(c1);
            LearningObjective lo4 = new LearningObjective(QuestionHolder.TST104Q, null);
            lo4.setCourse(c1);
            LearningObjective lo5 = new LearningObjective(QuestionHolder.TST105Q, null);
            lo5.setCourse(c1);
            LearningObjective lo6 = new LearningObjective(QuestionHolder.TST106Q, null);
            lo6.setCourse(c1);
            LearningObjective lo7 = new LearningObjective(QuestionHolder.TST107Q, null);
            lo7.setCourse(c1);
            LearningObjective lo8 = new LearningObjective(QuestionHolder.TST108Q, null);
            lo8.setCourse(c1);
            LearningObjective lo9 = new LearningObjective(QuestionHolder.TST109Q, null);
            lo9.setCourse(c1);
            LearningObjective lo10 = new LearningObjective(QuestionHolder.TST110Q, null);
            lo10.setCourse(c1);
            LearningObjective lo11 = new LearningObjective(QuestionHolder.TST111Q, null);
            lo11.setCourse(c1);
            LearningObjective lo12 = new LearningObjective(QuestionHolder.TST112Q, null);
            lo12.setCourse(c1);
            LearningObjective lo13 = new LearningObjective(QuestionHolder.TST113Q, null);
            lo13.setCourse(c1);
            em.persist(lo1);
            em.persist(lo2);
            em.persist(lo3);
            em.persist(lo4);
            em.persist(lo5);
            em.persist(lo6);
            em.persist(lo7);
            em.persist(lo8);
            em.persist(lo9);
            em.persist(lo10);
            em.persist(lo11);
            em.persist(lo12);
            em.persist(lo13);
            em.persist(c2);
            em.persist(c3);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public static void setupTestData(EntityManager em) {
        try {
            em.getTransaction().begin();
            Course c1 = new Course("Test", "TST", "https://datsoftlyngby.github.io/soft2020fall/TST/");
            Course c2 = new Course("System Integration", "SI", "https://datsoftlyngby.github.io/soft2020fall/SI/");
            Course c3 = new Course("Large Systems Development", "LSD", "https://datsoftlyngby.github.io/soft2020fall/LSD/");
            Course c4 = new Course("Discrete Mathematics", "DM", "https://datsoftlyngby.github.io/soft2020fall/DM/", false);
            LearningObjective lo1 = new LearningObjective("What is space?", "A neverending question... **SAPCE**");
            lo1.setCourse(c1);
            LearningObjective lo2 = new LearningObjective("What is space not?", "An answer...");
            lo2.setCourse(c1);
            LearningObjective lo3 = new LearningObjective("Explain that?", "Like this!");
            lo3.setCourse(c2);
            LearningObjective lo4 = new LearningObjective("Explain this?", "Like that!");
            lo4.setCourse(c3);
            LearningObjective lo5 = new LearningObjective("Wonder what wordering is", "A wonderful thing");
            lo5.setCourse(c4);
            em.persist(lo1);
            em.persist(lo2);
            em.persist(lo3);
            em.persist(lo4);
            em.persist(lo5);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public static void tearDownTestData(EntityManager em) {
        try {
            em.getTransaction().begin();
            em.createNamedQuery("LearningObjective.deleteAll").executeUpdate();
            em.createNamedQuery("Course.deleteAll").executeUpdate();
            em.createNativeQuery("ALTER TABLE `COURSE` AUTO_INCREMENT = 1").executeUpdate();
            em.createNativeQuery("ALTER TABLE `LEARNINGOBJECTIVE` AUTO_INCREMENT = 1").executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
