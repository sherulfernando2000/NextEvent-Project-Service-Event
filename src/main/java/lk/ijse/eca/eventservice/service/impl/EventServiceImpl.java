package lk.ijse.eca.eventservice.service.impl;

import lk.ijse.eca.eventservice.dto.EventDto;
import lk.ijse.eca.eventservice.entity.Event;
import lk.ijse.eca.eventservice.exception.DuplicateEventException;
import lk.ijse.eca.eventservice.exception.EventNotFoundException;
import lk.ijse.eca.eventservice.mapper.EventMapper;
import lk.ijse.eca.eventservice.repository.EventRepository;
import lk.ijse.eca.eventservice.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    @Override
    @Transactional
    public EventDto createEvent(EventDto dto) {
        log.debug("Creating event with ID: {}", dto.getEventId());

        if (eventRepository.existsById(dto.getEventId())) {
            log.warn("Duplicate event ID detected: {}", dto.getEventId());
            throw new DuplicateEventException(dto.getEventId());
        }

        Event saved = eventRepository.save(eventMapper.toEntity(dto));
        log.info("Event created successfully: {}", saved.getEventId());
        return eventMapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public EventDto getEvent(String eventId) {
        log.debug("Fetching event with ID: {}", eventId);
        return eventRepository.findById(eventId)
                .map(eventMapper::toDto)
                .orElseThrow(() -> {
                    log.warn("Event not found: {}", eventId);
                    return new EventNotFoundException(eventId);
                });
    }

    @Override
    @Transactional(readOnly = true)
    public List<EventDto> getAllEvents() {
        log.debug("Fetching all events");
        List<EventDto> events = eventRepository.findAll()
                .stream()
                .map(eventMapper::toDto)
                .toList();
        log.debug("Fetched {} event(s)", events.size());
        return events;
    }

    @Override
    @Transactional
    public EventDto updateEvent(String eventId, EventDto dto) {
        log.debug("Updating event with ID: {}", eventId);

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> {
                    log.warn("Event not found for update: {}", eventId);
                    return new EventNotFoundException(eventId);
                });

        eventMapper.updateEntity(dto, event);
        Event updated = eventRepository.save(event);
        log.info("Event updated successfully: {}", updated.getEventId());
        return eventMapper.toDto(updated);
    }

    @Override
    @Transactional
    public void deleteEvent(String eventId) {
        log.debug("Deleting event with ID: {}", eventId);

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> {
                    log.warn("Event not found for deletion: {}", eventId);
                    return new EventNotFoundException(eventId);
                });

        eventRepository.delete(event);
        log.info("Event deleted successfully: {}", eventId);
    }
}
