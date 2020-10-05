package DTOs;

import entities.LearningObjective;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LearningObjectivesDTO implements Serializable {

    private final List<LearningObjectiveDTO> learningObjectiveDTOs;

    public LearningObjectivesDTO(List<LearningObjective> learningObjectives) {
        learningObjectiveDTOs = new ArrayList();
        learningObjectives.forEach(learningObjective -> {
            learningObjectiveDTOs.add(new LearningObjectiveDTO(learningObjective));
        });
    }

    public List<LearningObjectiveDTO> getLearningObjectiveDTOs() {
        return learningObjectiveDTOs;
    }

}
