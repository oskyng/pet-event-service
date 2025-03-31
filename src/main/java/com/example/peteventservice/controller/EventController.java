package com.example.peteventservice.controller;

import com.example.peteventservice.model.Event;
import com.example.peteventservice.service.EventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/event")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<Event> getEvents() {
        return eventService.getEvents();
    }

    @GetMapping("/{id}")
    public Optional<Event> getEventById(@PathVariable int id) {
        return eventService.getEvent(id);
    }
}
