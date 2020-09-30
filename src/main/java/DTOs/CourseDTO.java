package DTOs;

import java.io.Serializable;
import entities.Course;

public class CourseDTO implements Serializable {
    
    private String name;
    private String shortName;
    private String link;

    public CourseDTO(Course course) {
        this.name = course.getName();
        this.shortName = course.getShortName();
        this.link = course.getLink();
    }
}
