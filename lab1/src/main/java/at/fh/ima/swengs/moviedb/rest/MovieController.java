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
public class MovieController {
 
    @Autowired
    private ActorRepository actorRepository;
 
    @Autowired
    private MovieRepository movieRepository;
 
    @Autowired
    private GenreRepository genreRepository;
 
    // ----------------------------------------------------------------------------
    @RequestMapping(value="/movies", method = RequestMethod.GET)
    // ----------------------------------------------------------------------------
    ResponseEntity<Iterable<Movie>> getAllMovies() {
        Iterable<Movie> movies = movieRepository.findAll();
        if (movies == null || !movies.iterator().hasNext()){
            return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(movies, HttpStatus.OK);
        }
    }
 
    // ----------------------------------------------------------------------------
    @RequestMapping(value="/movies/{id}", method = RequestMethod.GET)
    // ----------------------------------------------------------------------------
    ResponseEntity<Movie> getMovie(@PathVariable long id) {
        Optional<Movie> movieOptional = movieRepository.findById(id);
        if (movieOptional.isPresent()) {
            return new ResponseEntity<>(movieOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
 
 
    // ----------------------------------------------------------------------------
    @RequestMapping(value="/movies/{id}", method = RequestMethod.DELETE)
    // ----------------------------------------------------------------------------
    ResponseEntity<Movie> deleteMovie(@PathVariable long id) {
        Optional<Movie> movieOptional = movieRepository.findById(id);
        if (!movieOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        movieRepository.delete(movieOptional.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
 
    // ----------------------------------------------------------------------------
    @RequestMapping(value = "/movies", method = RequestMethod.POST)
    // ----------------------------------------------------------------------------
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie, UriComponentsBuilder ucBuilder) {
        movieRepository.save(movie);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("movies/{id}").buildAndExpand(movie.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
 
    }
 
    // ----------------------------------------------------------------------------
    @RequestMapping(value = "/movies/{id}", method = RequestMethod.PUT)
    // ----------------------------------------------------------------------------
    public ResponseEntity<Movie> updateMovie(@PathVariable long id,@RequestBody Movie movieUpdate) {

        Optional<Movie> movieOptional = movieRepository.findById(id);
        if (!movieOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        movieUpdate.setId(id);
        movieRepository.save(movieUpdate);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
 
}