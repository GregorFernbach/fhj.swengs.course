package at.fh.ima.swengs.moviedbv2.rest;


import at.fh.ima.swengs.moviedbv2.dto.ActorConverter;
import at.fh.ima.swengs.moviedbv2.dto.ActorDto;
import at.fh.ima.swengs.moviedbv2.model.Actor;
import at.fh.ima.swengs.moviedbv2.model.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
public class ActorRestController {

    @Autowired
    ActorRepository actorRepository;

    // ---------------------------------------------------------------------------------
    @RequestMapping(value="/actors", method = RequestMethod.GET)
    // ---------------------------------------------------------------------------------
    //Iterable<Actor> getAllActors() {
    ResponseEntity<List<ActorDto>> getAllActors() {

        Iterable<Actor> actors = actorRepository.findAll();
        if (actors==null||!actors.iterator().hasNext())
            return new ResponseEntity<List<ActorDto>>(HttpStatus.NO_CONTENT);

        List<ActorDto> dtos = new ArrayList<>();
        for (Actor actor : actors) {
            dtos.add(ActorConverter.convertToDTO(actor));
        }

        return new ResponseEntity<List<ActorDto>>(dtos,HttpStatus.OK);
    }

    // ---------------------------------------------------------------------------------
    @RequestMapping(value="/actors/{id}", method = RequestMethod.GET)
    // ---------------------------------------------------------------------------------
    //hole die entity aus der Datenbank
    ResponseEntity<ActorDto> getActor(@PathVariable long id) {
        Optional<Actor> actorOptional = actorRepository.findById(id);
        if (!actorOptional.isPresent())
            return new ResponseEntity<ActorDto>(HttpStatus.NOT_FOUND);

        //andere Klasse wandel Object um für das Frontend
        ActorDto actorDto = ActorConverter.convertToDTO(actorOptional.get());
        return new ResponseEntity<ActorDto>(actorDto,HttpStatus.OK);
    }

    // ---------------------------------------------------------------------------------
    @RequestMapping(value="/actors/{id}", method = RequestMethod.DELETE)
    // ---------------------------------------------------------------------------------
    ResponseEntity<ActorDto> deleteActor(@PathVariable long id) {
        Optional<Actor> actorOptional = actorRepository.findById(id);
        if (!actorOptional.isPresent())
            return new ResponseEntity<ActorDto>(HttpStatus.NOT_FOUND);

        actorRepository.delete(actorOptional.get());
        return new ResponseEntity<ActorDto>(HttpStatus.NO_CONTENT);
    }

    // ---------------------------------------------------------------------------------
    // Create actor
    // ---------------------------------------------------------------------------------
    @RequestMapping(value = "/actors", method = RequestMethod.POST)
    // ---------------------------------------------------------------------------------
    public ResponseEntity<ActorDto> createActor(@RequestBody ActorDto actorDTO, UriComponentsBuilder ucBuilder) {

        Actor actorModel = ActorConverter.convertToModel(actorDTO);

        actorRepository.save(actorModel);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/actors/{id}").buildAndExpand(actorModel.getId()).toUri());
        return new ResponseEntity<ActorDto>(headers, HttpStatus.CREATED);
    }

    // ---------------------------------------------------------------------------------
    // Update actor
    // ---------------------------------------------------------------------------------
    @RequestMapping(value = "/actors/{id}", method = RequestMethod.PUT)
    // ---------------------------------------------------------------------------------
    public ResponseEntity<ActorDto> updateActor(@PathVariable long id,@RequestBody ActorDto actorDtoUpdate) {

        Optional<Actor> actorOptional = actorRepository.findById(id);
        if (!actorOptional.isPresent())
            return new ResponseEntity<ActorDto>(HttpStatus.NOT_FOUND);

        Actor actor = actorOptional.get();

        actor.setFirstName(actorDtoUpdate.getFirstName());
        actor.setLastName(actorDtoUpdate.getLastName());
        actor.setDayOfBirth(actorDtoUpdate.getDayOfBirth());

        actorRepository.save(actor);

        return new ResponseEntity<ActorDto>(HttpStatus.NO_CONTENT);
    }


}
