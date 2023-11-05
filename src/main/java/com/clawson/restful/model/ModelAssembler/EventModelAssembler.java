package com.clawson.restful.model.ModelAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
//import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.clawson.restful.controller.EventController;
import com.clawson.restful.model.Event;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class EventModelAssembler implements RepresentationModelAssembler<Event, EntityModel<Event>> {

    @Override
    public EntityModel<Event> toModel(Event event){

        return EntityModel.of(event,
                linkTo(methodOn(EventController.class).getEvent(event.getId())).withSelfRel(),
                linkTo(methodOn(EventController.class).getAllEvents()).withRel("events"));
    }
}
