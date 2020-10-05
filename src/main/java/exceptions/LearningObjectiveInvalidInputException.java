package exceptions;

public class LearningObjectiveInvalidInputException extends Exception {

    public LearningObjectiveInvalidInputException() {
        super("400 - LearningObjective Invalid Input");
    }

    public LearningObjectiveInvalidInputException(String message) {
        super(message);
    }

}
