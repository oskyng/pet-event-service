package com.example.peteventservice.controller;

import com.example.peteventservice.dto.CreateEventRequest;
import com.example.peteventservice.dto.UpdateEventRequest;
import com.example.peteventservice.model.Event;
import com.example.peteventservice.dto.ResponseWrapper;
import com.example.peteventservice.service.EventService;
import com.example.peteventservice.service.IEventService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/event")
public class EventController {
    private final IEventService eventService;

    public EventController(IEventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<?> getEvents() {
        log.info("GET /event - Obteniendo todos los eventos");
        return ResponseEntity.ok(new ResponseWrapper<>(HttpStatus.OK.value(), "OK", eventService.getEvents().size(), eventService.getEvents()));
    }

    @GetMapping("/{id}")
    public Event getEventById(@PathVariable Long id) {
        log.info("GET /event/{} - Obteniendo el evento con id: {}", id);
        return eventService.getEventById(id);
    }

    @PostMapping
    public ResponseEntity<?> createEvent(@Valid @RequestBody CreateEventRequest event) {
        log.info("POST /event - Creando el evento: {}", event);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper<>(HttpStatus.CREATED.value(), "Evento creado exitosamente", 1, List.of(eventService.createEvent(event))));
    }

    @PutMapping
    public ResponseEntity<?> updateEvent(@Valid @RequestBody UpdateEventRequest event) {
        log.info("PUT /event - Actualizando el evento: {}", event.getName());
        return ResponseEntity.ok(new ResponseWrapper<>(HttpStatus.OK.value(), "Evento actualizado exitosamente", 1, List.of(eventService.updateEvent(event))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long id) {
        log.info("DELETE /event/{} - Eliminando el evento con id: {}", id);
        eventService.deleteEvent(id);
        return ResponseEntity.ok(new ResponseWrapper<>(HttpStatus.OK.value(), "Evento eliminado exitosamente", 1, null));
    }
}
