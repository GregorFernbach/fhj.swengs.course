package at.fh.ima.swengs.moviedb.model;
 
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
 
@Entity
public class Genre {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
 
    private String title;
 
    private String description;
 
    @OneToMany(mappedBy = "genre")
    private List<Movie> movies;
 
    @Version
    @JsonIgnore
    private long version;

    public Genre() {
    }

    public Genre(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Movie> getMovies() {
        return movies;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", movies=" + movies +
                ", version=" + version +
                '}';
    }
}