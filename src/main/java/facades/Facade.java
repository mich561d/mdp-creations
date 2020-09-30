package facades;

import DTOs.CourseDTO;
import entities.Course;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

public class Facade implements ICourseFacade {

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
}
