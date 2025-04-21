package com.example.peteventservice.service;

import com.example.peteventservice.dto.CreateEventRequest;
import com.example.peteventservice.dto.UpdateEventRequest;
import com.example.peteventservice.model.Event;

import java.util.List;

public interface IEventService {
    List<Event> getEvents();
    Event getEventById(Long id);
    Event createEvent(CreateEventRequest request);
    Event updateEvent(UpdateEventRequest request);
    void deleteEvent(Long id);
}
