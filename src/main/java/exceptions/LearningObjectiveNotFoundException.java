package exceptions;

public class LearningObjectiveNotFoundException extends Exception {

    public LearningObjectiveNotFoundException() {
        super("404 - LearningObjective Not Found");
    }

    public LearningObjectiveNotFoundException(String message) {
        super(message);
    }

}
