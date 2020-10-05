package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name = "LearningObjective.deleteAll", query = "DELETE from LearningObjective"),
    @NamedQuery(name = "LearningObjective.count", query = "SELECT COUNT(lo) FROM LearningObjective lo"),
    @NamedQuery(name = "LearningObjective.findById", query = "SELECT lo FROM LearningObjective lo WHERE lo.id = :id"),
    /*@NamedQuery(name = "LearningObjective.findByRandom", query = "SELECT lo FROM LearningObjective lo ORDER BY FUNCTION('RAND') LIMIT 1"),*/
    @NamedQuery(name = "LearningObjective.findAll", query = "SELECT lo FROM LearningObjective lo"),
    @NamedQuery(name = "LearningObjective.findAllByCourseId", query = "SELECT lo FROM LearningObjective lo WHERE lo.course.id = :courseId"),
    @NamedQuery(name = "LearningObjective.findAllByCourseShortName", query = "SELECT lo FROM LearningObjective lo WHERE lo.course.shortName = :courseShortName")
})
public class LearningObjective implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Course course;
    @Column(nullable = false, unique = true)
    private String question;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column()
    private String answer;

    public LearningObjective() {
    }

    public LearningObjective(String question, String answer) {
        this.course = course;
        this.question = question;
        this.answer = answer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        if (course != null) {
            course.addLearningObjective(this);
        }
        this.course = course;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
