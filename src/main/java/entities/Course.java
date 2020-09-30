package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name = "Course.deleteAll", query = "DELETE from Course"),
    @NamedQuery(name = "Course.findAll", query = "SELECT c FROM Course c WHERE c.active = true")
})
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 45, nullable = false, unique = true)
    private String name;
    @Column(length = 5, nullable = false, unique = true)
    private String shortName;
    @Column(length = 255, nullable = false, unique = true)
    private String link;
    private boolean active;

    public Course() {
    }

    public Course(String name, String shortName, String link) {
        this.name = name;
        this.shortName = shortName;
        this.link = link;
        this.active = true;
    }
    
    public Course(String name, String shortName, String link, boolean active) {
        this.name = name;
        this.shortName = shortName;
        this.link = link;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public String getLink() {
        return link;
    }

    public boolean isActive() {
        return active;
    }
}
