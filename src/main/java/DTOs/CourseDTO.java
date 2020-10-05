package DTOs;

import java.io.Serializable;
import entities.Course;

public class CourseDTO implements Serializable {

    private final String name;
    private final String shortName;
    private final String link;

    public CourseDTO(Course course) {
        this.name = course.getName();
        this.shortName = course.getShortName();
        this.link = course.getLink();
    }
}
