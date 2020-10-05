package DTOs;

import entities.LearningObjective;
import java.io.Serializable;

public class LearningObjectiveDTO implements Serializable {

    private final CourseDTO course;
    private final String question;
    private final String answer;

    public LearningObjectiveDTO(LearningObjective learningObjective) {
        course = new CourseDTO(learningObjective.getCourse());
        question = learningObjective.getQuestion();
        answer = learningObjective.getAnswer();
    }
}
