package at.fh.ima.swengs.moviedb.rest;
 
import at.fh.ima.swengs.moviedb.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
 
import java.util.Optional;
 
@RestController
public class MovieGenreController {
 
    @Autowired
    private ActorRepository actorRepository;
 
    @Autowired
    private MovieRepository movieRepository;
 
    @Autowired
    private GenreRepository genreRepository;
 
    // ----------------------------------------------------------------------------
    @RequestMapping(value="/movies/{idm}/genre", method = RequestMethod.DELETE)
    // ----------------------------------------------------------------------------
    ResponseEntity removeGenreFromMovie(@PathVariable long idm) {
 
        Optional<Movie> movieOptional = movieRepository.findById(idm);
        if (!movieOptional.isPresent())
            return new ResponseEntity(HttpStatus.NOT_FOUND);
 
        Movie movie = movieOptional.get();
 
        movie.setGenre(null);
        movieRepository.save(movie);
        return new ResponseEntity<Movie>(HttpStatus.NO_CONTENT);
    }
 
    // ----------------------------------------------------------------------------
    @RequestMapping(value = "/movies/{idm}/genre/{idg}", method = {RequestMethod.POST,RequestMethod.PUT})
    // ----------------------------------------------------------------------------
    public ResponseEntity<Movie> updateGenreInMovie(@PathVariable long idm,@PathVariable long idg, UriComponentsBuilder ucBuilder) {
 
        Optional<Movie> movieOptional = movieRepository.findById(idm);
        if (!movieOptional.isPresent())
            return new ResponseEntity(HttpStatus.NOT_FOUND);
 
        Movie movie = movieOptional.get();
 
        Optional<Genre> genreOptional = genreRepository.findById(idg);
        if (!genreOptional.isPresent())
            return new ResponseEntity(HttpStatus.NOT_FOUND);
 
        Genre genre = genreOptional.get();
        movie.setGenre(genre);
 
        movieRepository.save(movie);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/movies/{idm}/genre/{idg}").buildAndExpand(movie.getId(),genre.getId()).toUri());
        return new ResponseEntity<Movie>(headers, HttpStatus.CREATED);
    }
 
 
}