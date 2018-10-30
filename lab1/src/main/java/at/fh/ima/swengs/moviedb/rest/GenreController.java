package at.fh.ima.swengs.moviedb.rest;
 
import at.fh.ima.swengs.moviedb.model.Genre;
import at.fh.ima.swengs.moviedb.model.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
 
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
 
import java.util.Optional;
 
@RestController
public class GenreController {
 
    @Autowired
    private GenreRepository  genreRepository;
 
 
    // ----------------------------------------------------------------------------
    @RequestMapping(value="/genres", method = RequestMethod.GET)
    // ----------------------------------------------------------------------------
    ResponseEntity<Iterable<Genre>> getAllGenres() {
        Iterable<Genre> genres = genreRepository.findAll();
        if (genres == null || !genres.iterator().hasNext()){
            return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(genres, HttpStatus.OK);
        }
    }
 
    // ----------------------------------------------------------------------------
    @RequestMapping(value="/genres/{id}", method = RequestMethod.GET)
    // ----------------------------------------------------------------------------
    ResponseEntity<Genre> getGenre(@PathVariable long id) {
        Optional<Genre> genreOptional = genreRepository.findById(id);
        if (genreOptional.isPresent()) {
            return new ResponseEntity<>(genreOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }
 
 
    // ----------------------------------------------------------------------------
    @RequestMapping(value="/genres/{id}", method = RequestMethod.DELETE)
    // ----------------------------------------------------------------------------
    ResponseEntity<Genre> deleteGenre(@PathVariable long id) {
        Optional<Genre> genreOptional = genreRepository.findById(id);
        if (!genreOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        genreRepository.delete(genreOptional.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
 
    // ----------------------------------------------------------------------------
    @RequestMapping(value = "/genres", method = RequestMethod.POST)
    // ----------------------------------------------------------------------------
    public ResponseEntity<Genre> createGenre(@RequestBody Genre genre, UriComponentsBuilder ucBuilder) {

        genreRepository.save(genre);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("genres/{id}").buildAndExpand(genre.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
 
    // ----------------------------------------------------------------------------
    @RequestMapping(value = "/genres/{id}", method = RequestMethod.PUT)
    // ----------------------------------------------------------------------------
    public ResponseEntity<Genre> updateGenre(@PathVariable long id,@RequestBody Genre genreUpdate) {
        Optional<Genre> genreOptional = genreRepository.findById(id);
        if (!genreOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        genreUpdate.setId(id);
        genreRepository.save(genreUpdate);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
 
 
}