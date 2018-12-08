package at.fh.ima.swengs.moviedbv2.dto;

import at.fh.ima.swengs.moviedbv2.model.Actor;
import at.fh.ima.swengs.moviedbv2.model.Genre;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

public class MovieDto {

    private long id;

    private String title;

    private String plot;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern="dd.MM.yyyy")
    private Date releaseDate;

    private String genre;

    private int length;
    private boolean color;


    public MovieDto() {
    }

    public MovieDto(String title, String plot, Date releaseDate, int length, boolean color) {
        this.title = title;
        this.plot = plot;
        this.releaseDate = releaseDate;
        this.length = length;
        this.color = color;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }



}
