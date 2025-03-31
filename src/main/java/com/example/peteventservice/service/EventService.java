package com.example.peteventservice.service;

import com.example.peteventservice.model.Event;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    private final List<Event> events = new ArrayList<>();

    public EventService() {
        LocalDateTime now = LocalDateTime.now();

        events.add(new Event(1, "ferias", "sadfsafasfsa adasdsadasfsa", now));
        events.add(new Event(2, "competiciones", "sdewarwaf adafawrfa fafasfawfa", now));
        events.add(new Event(3, "Servicio veterinario", "d adad assadqaw adwaddwarf", now));
    }

    public List<Event> getEvents() {
        return events;
    }

    public Optional<Event> getEvent(int id) {
        return events.stream().filter(e -> e.getId() == id).findFirst();
    }
}
