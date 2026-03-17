package lk.ijse.eca.eventservice.service;

import lk.ijse.eca.eventservice.dto.EventDto;

import java.util.List;

public interface EventService {

    EventDto createEvent(EventDto dto);

    EventDto getEvent(String eventId);

    List<EventDto> getAllEvents();

    EventDto updateEvent(String eventId, EventDto dto);

    void deleteEvent(String eventId);
}
