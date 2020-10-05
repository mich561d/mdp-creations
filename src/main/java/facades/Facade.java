package facades;

import DTOs.CourseDTO;
import DTOs.LearningObjectiveDTO;
import DTOs.LearningObjectivesDTO;
import entities.Course;
import entities.LearningObjective;
import exceptions.GenericExceptionMapper;
import exceptions.LearningObjectiveInvalidInputException;
import exceptions.LearningObjectiveNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class Facade implements ICourseFacade, ILearningObjectiveFacade {

    private static Facade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private Facade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static Facade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new Facade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public List<CourseDTO> getAllCourses() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Course> query = em.createNamedQuery("Course.findAll", Course.class);
            List<CourseDTO> result = new ArrayList();
            for (Course course : query.getResultList()) {
                result.add(new CourseDTO(course));
            }
            return result;
        } finally {
            em.close();
        }
    }

    @Override
    public LearningObjectiveDTO getLearningObjectiveById(int id) throws LearningObjectiveNotFoundException {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<LearningObjective> query = em.createNamedQuery("LearningObjective.findById", LearningObjective.class).setParameter("id", id);
            return new LearningObjectiveDTO(query.getSingleResult());
        } catch (NoResultException exception) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, exception);
            throw new LearningObjectiveNotFoundException("Invalid id");
        } finally {
            em.close();
        }
    }

    @Override
    public LearningObjectiveDTO getLearningObjectiveByRandom() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Long> count = em.createNamedQuery("LearningObjective.count", Long.class);
            int randomId = new Random().nextInt(count.getSingleResult().intValue()) + 1;
            TypedQuery<LearningObjective> query = em.createNamedQuery("LearningObjective.findById", LearningObjective.class).setParameter("id", randomId);
            return new LearningObjectiveDTO(query.getSingleResult());
        } finally {
            em.close();
        }
    }

    @Override
    public LearningObjectivesDTO getAllLearningObjectives() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<LearningObjective> query = em.createNamedQuery("LearningObjective.findAll", LearningObjective.class);
            return new LearningObjectivesDTO(query.getResultList());
        } finally {
            em.close();
        }
    }

    @Override
    public LearningObjectivesDTO getAllLearningObjectivesByCourseId(int courseId) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<LearningObjective> query = em.createNamedQuery("LearningObjective.findAllByCourseId", LearningObjective.class).setParameter("courseId", courseId);
            return new LearningObjectivesDTO(query.getResultList());
        } finally {
            em.close();
        }
    }

    @Override
    public LearningObjectivesDTO getAllLearningObjectivesByCourseShortName(String shortName) throws LearningObjectiveInvalidInputException {
        if (shortName == null) {
            throw new LearningObjectiveInvalidInputException("Invalid input");
        }
        EntityManager em = getEntityManager();
        try {
            TypedQuery<LearningObjective> query = em.createNamedQuery("LearningObjective.findAllByCourseShortName", LearningObjective.class).setParameter("courseShortName", shortName);
            return new LearningObjectivesDTO(query.getResultList());
        } finally {
            em.close();
        }
    }
}
