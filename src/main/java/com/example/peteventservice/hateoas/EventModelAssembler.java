package com.example.peteventservice.hateoas;

import com.example.peteventservice.controller.EventController;
import com.example.peteventservice.model.Event;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class EventModelAssembler implements RepresentationModelAssembler<Event, EntityModel<Event>> {
    @Override
    public EntityModel<Event> toModel(Event entity) {
        return EntityModel.of(
                entity,
                linkTo(methodOn(EventController.class).getEventById(entity.getId())).withSelfRel(),
                linkTo(methodOn(EventController.class).getEvents()).withRel("events"),
                linkTo(methodOn(EventController.class).updateEvent(null)).withRel("Update event"),
                linkTo(methodOn(EventController.class).deleteEvent(entity.getId())).withRel("Delete event")
        );
    }
}
