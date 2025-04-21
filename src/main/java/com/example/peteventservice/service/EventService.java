package com.example.peteventservice.service;

import com.example.peteventservice.dto.CreateEventRequest;
import com.example.peteventservice.dto.UpdateEventRequest;
import com.example.peteventservice.exception.EventNotFoundException;
import com.example.peteventservice.model.Event;
import com.example.peteventservice.repository.IEventRepository;
import com.example.peteventservice.repository.IParticipantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EventService implements IEventService{
    @Autowired
    private IEventRepository eventRepository;

    public List<Event> getEvents() {
        log.debug("Servicio: getEvents()");
        return eventRepository.findAll(Sort.by("id").ascending());
    }

    public Event getEventById(Long id) {
        log.debug("Servicio: getEventById({})", id);
        return eventRepository.findById(id).orElseThrow(() -> new EventNotFoundException(id));
    }

    public Event createEvent(CreateEventRequest request) {
        log.debug("Servicio: createEvent({})", request.getName());
        Event event = new Event();
        event.setName(request.getName());
        event.setDescription(request.getDescription());
        event.setDateEvent(request.getDateEvent());

        return eventRepository.save(event);
    }

    public Event updateEvent(UpdateEventRequest request) {
        log.debug("Servicio: updateEvent({})", request.getId());
        Optional<Event> foundEvent = eventRepository.findById(request.getId());
        return foundEvent.map(event -> {
            event.setName(request.getName() != null ? request.getName() : event.getName());
            event.setDescription(request.getDescription() != null ? request.getDescription() : event.getDescription());
            event.setDateEvent(request.getDateEvent() != null ? request.getDateEvent() : event.getDateEvent());

            return eventRepository.save(event);
        }).orElseThrow(() -> new EventNotFoundException(request.getId()));
    }

    public void deleteEvent(Long id) {
        log.debug("Servicio: deleteEvent({})", id);
        Event foundEvent = eventRepository.findById(id).orElseThrow(() -> new EventNotFoundException(id));
        eventRepository.delete(foundEvent);
    }
}
