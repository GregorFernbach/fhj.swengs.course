package at.fh.ima.swengs.moviedb.model;
 
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
 
import javax.persistence.*;
import java.util.Date;
import java.util.List;
 
@Entity
 
public class Movie {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
 
    private String title;
 
    private String plot;
 
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd.MM.yyyy")
    private Date releaseDate;
 
    @ManyToOne
    private Genre genre;
 
    private int length;
    private boolean color;
 
    @ManyToMany
    private List<Actor> actors;
 
    @Version
    @JsonIgnore
    private long version;

    public Movie() {
    }

    public Movie(String title, String plot, Date releaseDate, int length, boolean color) {
        this.title = title;
        this.plot = plot;
        this.releaseDate = releaseDate;
        this.length = length;
        this.color = color;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPlot() {
        return plot;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public Genre getGenre() {
        return genre;
    }

    public int getLength() {
        return length;
    }

    public boolean isColor() {
        return color;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public long getVersion() {
        return version;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", plot='" + plot + '\'' +
                ", releaseDate=" + releaseDate +
                ", genre=" + genre +
                ", length=" + length +
                ", color=" + color +
                ", actors=" + actors +
                ", version=" + version +
                '}';
    }
}