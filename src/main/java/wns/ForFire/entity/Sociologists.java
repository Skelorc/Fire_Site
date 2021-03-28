package wns.ForFire.entity;


import javax.persistence.*;

@Entity
@Table(name = "sociologists")
public class Sociologists {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @Column(columnDefinition="TEXT")
    private String biografy;

    @Column(columnDefinition="TEXT")
    private String work;
    private String url_image;

    public Sociologists(String name, String biografy, String url_image) {
        this.name = name;
        this.biografy = biografy;
        this.url_image = url_image;
    }

    public Sociologists(String name, String biografy, String work, String url_image) {
        this.name = name;
        this.biografy = biografy;
        this.work = work;
        this.url_image = url_image;
    }

    public Sociologists(int id, String name, String biografy, String work, String url_image) {
        this.id = id;
        this.name = name;
        this.biografy = biografy;
        this.work = work;
        this.url_image = url_image;
    }

    public Sociologists(int id, String name, String biografy, String url_image) {
        this.id = id;
        this.name = name;
        this.biografy = biografy;
        this.url_image = url_image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBiografy() {
        return biografy;
    }

    public void setBiografy(String biografy) {
        this.biografy = biografy;
    }

    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }

    public Sociologists() {
    }
}
