package com.clawson.restful.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import com.clawson.restful.model.ModelAssembler.EventModelAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.clawson.restful.exceptions.EventNotFoundException;
import com.clawson.restful.model.Event;
import com.clawson.restful.repository.EventRepository;

import javax.swing.text.html.parser.Entity;

@RestController
public class EventController {

    private final EventRepository repo;
    private final EventModelAssembler assembler;

    EventController(EventRepository repo, EventModelAssembler assembler) {
        this.repo = repo;
        this.assembler = assembler;
    }

    @GetMapping("/events")
    public CollectionModel<EntityModel<Event>> getAllEvents() {
        List<EntityModel<Event>> events = repo.findAll().stream()
                .map(assembler::toModel)
                .toList();
        return CollectionModel.of(events, linkTo(methodOn(EventController.class).getAllEvents()).withSelfRel());
    }

    @PostMapping("/events")
    public ResponseEntity<EntityModel<Event>> createNewEvent(@RequestBody Event event) {
        EntityModel<Event> entityModel = assembler.toModel(repo.save(event));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @GetMapping("/events/{id}")
    public EntityModel<Event> getEvent(@PathVariable Long id) {
        Event event = repo.findById(id).orElseThrow(() -> new EventNotFoundException(id));

        return assembler.toModel(event);
    }

    @PutMapping("events/{id}")
    public ResponseEntity<EntityModel<Event>> updateEvent(@RequestBody Event newEvent, @PathVariable Long id) {

        Event updatedEvent = repo.findById(id).map(event -> {
            event.setAddress(newEvent.getAddress());
            event.setEventDate(newEvent.getEventDate());
            event.setEventDescription(newEvent.getEventDescription());
            event.setEventName(newEvent.getEventName());
            event.setEventPlatform(newEvent.getEventPlatform());
            event.setEventPrice(newEvent.getEventPrice());
            event.setFree(newEvent.isFree());
            return repo.save(event);
        }).orElseGet(() -> {
            newEvent.setId(id);
            return repo.save(newEvent);
        });

        EntityModel<Event> entityModel = assembler.toModel(updatedEvent);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/events/{id}")
    public ResponseEntity<EntityModel<Event>> deleteEvent(@PathVariable Long id) {
        repo.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
