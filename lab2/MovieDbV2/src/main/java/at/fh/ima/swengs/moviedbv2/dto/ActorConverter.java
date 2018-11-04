package at.fh.ima.swengs.moviedbv2.dto;

import at.fh.ima.swengs.moviedbv2.model.Actor;
import at.fh.ima.swengs.moviedbv2.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class ActorConverter {
    //Bindeglied zwischen Datenbank und Frontend
    // Can be improved with ModelMapper (http://modelmapper.org/)

    public static Actor convertToModel(ActorDto actorDto) {

        Actor actorModel = new Actor();
        actorModel.setId(actorDto.getId());
        actorModel.setFirstName(actorDto.getFirstName());
        actorModel.setLastName(actorDto.getLastName());
        actorModel.setDayOfBirth(actorDto.getDayOfBirth());
        return actorModel;
    }

    public static ActorDto convertToDTO(Actor actorModel) {

        ActorDto actorDto = new ActorDto();

        actorDto.setId(actorModel.getId());
        actorDto.setFirstName(actorModel.getFirstName());
        actorDto.setLastName(actorModel.getLastName());
        actorDto.setDayOfBirth(actorModel.getDayOfBirth());

        convertMovies(actorModel, actorDto);
        return actorDto;
    }

    private static void convertMovies(Actor actorModel, ActorDto actorDto) {
        if (actorModel.getMovies() == null) return;

        List<MovieDto> movies = new ArrayList<>();
        for (Movie movieModel : actorModel.getMovies()) {

            MovieDto movieDto = new MovieDto();

            movieDto.setId(movieModel.getId());
            movieDto.setTitle(movieModel.getTitle());
            movieDto.setPlot(movieModel.getPlot());
            movieDto.setGenre(movieModel.getGenre().getTitle());
            movieDto.setLength(movieModel.getLength());
            movieDto.setReleaseDate(movieModel.getReleaseDate());
            actorDto.addMovie(movieDto);
        }
    }

}
