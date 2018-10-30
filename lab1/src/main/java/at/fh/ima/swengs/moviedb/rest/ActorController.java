package at.fh.ima.swengs.moviedb.rest;
 
import at.fh.ima.swengs.moviedb.model.Actor;
import at.fh.ima.swengs.moviedb.model.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
 
import java.util.Optional;
 
@RestController
public class ActorController {
 
    @Autowired
    private ActorRepository  actorRepository;
 
    // ----------------------------------------------------------------------------
    @RequestMapping(value="/actors1", method = RequestMethod.GET)
    // ----------------------------------------------------------------------------
    Iterable<Actor> getAllActorsSimple() {
        Iterable<Actor> actors = actorRepository.findAll();
        if (actors == null || !actors.iterator().hasNext()) {
            return null;
        }
        return actors;
    }
 
    // ----------------------------------------------------------------------------
    @RequestMapping(value="/actors", method = RequestMethod.GET)
    // ----------------------------------------------------------------------------
    ResponseEntity<Iterable<Actor>> getAllActors() {

        Iterable<Actor> actors = actorRepository.findAll();
        if (actors==null || !actors.iterator().hasNext()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(actors, HttpStatus.OK);
    }
 
    // ----------------------------------------------------------------------------
    @RequestMapping(value="/actors/{id}", method = RequestMethod.GET)
    // ----------------------------------------------------------------------------
    Actor getActor(@PathVariable long id) {

        Optional<Actor> actorOptional = actorRepository.findById(id);
        if (actorOptional.isPresent())
            return actorOptional.get();
        else
            throw new ActorNotFoundException("Does not exist!");
    }

 
    // ----------------------------------------------------------------------------
    @RequestMapping(value="/actors/{id}", method = RequestMethod.DELETE)
    // ----------------------------------------------------------------------------
    ResponseEntity<Actor> deleteActor(@PathVariable long id) {
        Optional<Actor> actorOptional = actorRepository.findById(id);
        if (!actorOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Actor actor = actorOptional.get();
        actorRepository.delete(actor);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
 
    // ----------------------------------------------------------------------------
    @RequestMapping(value = "/actors", method = RequestMethod.POST)
    // ----------------------------------------------------------------------------
    public ResponseEntity<Actor> createActor(@RequestBody Actor actor, UriComponentsBuilder ucBuilder) {

        actorRepository.save(actor);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("actors/{id}").buildAndExpand(actor.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

 
    // ----------------------------------------------------------------------------
    @RequestMapping(value = "/actors/{id}", method = RequestMethod.PUT)
    // ----------------------------------------------------------------------------
    public ResponseEntity<Actor> updateActor(@PathVariable long id,@RequestBody Actor actorUpdate) {

        Optional<Actor> actorOptional = actorRepository.findById(id);
        if (!actorOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        actorUpdate.setId(id);
        actorRepository.save(actorUpdate);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
 
    @ResponseStatus(value= HttpStatus.NOT_FOUND,reason="This actor was not found in the system")
    @ExceptionHandler(ActorNotFoundException.class)
    public void exceptionHandler()
    {
 
    }
 
}