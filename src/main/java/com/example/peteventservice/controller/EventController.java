package com.example.peteventservice.controller;

import com.example.peteventservice.dto.CreateEventRequest;
import com.example.peteventservice.dto.UpdateEventRequest;
import com.example.peteventservice.hateoas.EventModelAssembler;
import com.example.peteventservice.model.Event;
import com.example.peteventservice.dto.ResponseWrapper;
import com.example.peteventservice.service.IEventService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Slf4j
@RestController
@RequestMapping("/event")
public class EventController {
    private final IEventService eventService;
    private final EventModelAssembler eventModelAssembler;

    public EventController(IEventService eventService, EventModelAssembler eventModelAssembler) {
        this.eventService = eventService;
        this.eventModelAssembler = eventModelAssembler;
    }

    @GetMapping
    public ResponseEntity<?> getEvents() {
        log.info("GET /event - Obteniendo todos los eventos");
        List<EntityModel<Event>> models = eventService.getEvents().stream().map(eventModelAssembler::toModel).collect(Collectors.toList());
        return ResponseEntity.ok(CollectionModel.of(models, linkTo(methodOn(EventController.class).getEvents()).withSelfRel()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEventById(@PathVariable Long id) {
        log.info("GET /event/{} - Obteniendo el evento con id: {}", id);
        return ResponseEntity.ok(eventModelAssembler.toModel(eventService.getEventById(id)));
    }

    @PostMapping
    public ResponseEntity<?> createEvent(@Valid @RequestBody CreateEventRequest event) {
        log.info("POST /event - Creando el evento: {}", event);
        return ResponseEntity.status(HttpStatus.CREATED).body(eventModelAssembler.toModel(eventService.createEvent(event)));
    }

    @PutMapping
    public ResponseEntity<?> updateEvent(@Valid @RequestBody UpdateEventRequest event) {
        log.info("PUT /event - Actualizando el evento: {}", event.getName());
        return ResponseEntity.ok(eventModelAssembler.toModel(eventService.updateEvent(event)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long id) {
        log.info("DELETE /event/{} - Eliminando el evento con id: {}", id);
        eventService.deleteEvent(id);
        return ResponseEntity.ok(new ResponseWrapper<>(HttpStatus.OK.value(), "Evento eliminado exitosamente", 1, null));
    }
}
