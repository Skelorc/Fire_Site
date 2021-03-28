package wns.ForFire.entity;


import javax.persistence.*;

@Entity
public class Terms {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(columnDefinition="TEXT")
    private String term;

    @Column(columnDefinition="TEXT")
    private String description;

    public Terms() {
    }

    public Terms(long id, String term, String description) {
        this.id = id;
        this.term = term;
        this.description = description;
    }

    public Terms(String term, String description) {
        this.term = term;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
