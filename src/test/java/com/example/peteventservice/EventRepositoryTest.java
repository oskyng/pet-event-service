package com.example.peteventservice;

import com.example.peteventservice.model.Event;
import com.example.peteventservice.model.Participant;
import com.example.peteventservice.repository.IEventRepository;
import com.example.peteventservice.repository.IParticipantRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class EventRepositoryTest {
    @Autowired
    private IEventRepository eventRepository;
    @Autowired
    private IParticipantRepository participantRepository;

    @Test
    public void testSaveAndFindById() {
        Participant participant = new Participant(null, "Oscar", "Sanzana", "19754400-7", "o.sanzana@duocuc.cl", 12345678, "Calle qwerre 123", null);
        Participant saveParticipant = participantRepository.save(participant);

        Event event = new Event(null, "Evento 1", "Es una prueba", LocalDateTime.now(), List.of(saveParticipant));

        Event saveEvent = eventRepository.save(event);
        Optional<Event> foundEvent = eventRepository.findById(saveEvent.getId());

        assertTrue(foundEvent.isPresent());

        assertEquals("Evento 1", foundEvent.get().getName());
    }
}
