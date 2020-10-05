package facades;

import DTOs.LearningObjectiveDTO;
import DTOs.LearningObjectivesDTO;
import exceptions.LearningObjectiveInvalidInputException;
import exceptions.LearningObjectiveNotFoundException;

public interface ILearningObjectiveFacade {

    public LearningObjectiveDTO getLearningObjectiveById(int id) throws LearningObjectiveNotFoundException;

    public LearningObjectiveDTO getLearningObjectiveByRandom();

    public LearningObjectivesDTO getAllLearningObjectives();

    public LearningObjectivesDTO getAllLearningObjectivesByCourseId(int id);

    public LearningObjectivesDTO getAllLearningObjectivesByCourseShortName(String shortName) throws LearningObjectiveInvalidInputException;
}
