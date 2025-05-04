package com.example.peteventservice;

import com.example.peteventservice.exception.EventNotFoundException;
import com.example.peteventservice.model.Event;
import com.example.peteventservice.model.Participant;
import com.example.peteventservice.repository.IEventRepository;
import com.example.peteventservice.service.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class EventServiceTest {
    private IEventRepository eventRepository;
    private EventService eventService;

    @BeforeEach
    public void init() {
        eventRepository = Mockito.mock(IEventRepository.class);
        eventService = new EventService(eventRepository);
    }

    @Test
    public void testGetAllEvents() {
        Participant participant1 = new Participant(1L, "Oscar", "Sanzana", "19754400-7", "o.sanzana@duocuc.cl", 12345678, "Calle qwerre 123", null);
        Participant participant2 = new Participant(2L, "Oscar", "Sanzana", "19754400-7", "o.sanzana@duocuc.cl", 12345678, "Calle qwerre 123", null);

        Event event1 = new Event(1L, "Evento 1", "Es una prueba", LocalDateTime.now(), List.of(participant1));
        Event event2 = new Event(2L, "Evento 2", "Es una prueba numero 2", LocalDateTime.now(), List.of(participant2));

        when(eventRepository.findAll(Sort.by("id").ascending())).thenReturn(Arrays.asList(event1, event2));

        List<Event> events = eventService.getEvents();
        assertEquals(2, events.size());
        assertEquals(event1, events.get(0));
    }

    @Test
    public void testGetEventByIdDontExist() {
        when(eventRepository.findById(100L)).thenReturn(Optional.empty());

        assertThrows(EventNotFoundException.class, () -> eventService.getEventById(100L));
    }
}
