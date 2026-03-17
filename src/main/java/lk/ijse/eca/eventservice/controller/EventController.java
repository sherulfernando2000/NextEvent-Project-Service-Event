package lk.ijse.eca.eventservice.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.groups.Default;
import lk.ijse.eca.eventservice.dto.EventDto;
import lk.ijse.eca.eventservice.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
@Slf4j
@Validated
public class EventController {

    private final EventService eventService;

    private static final String PROGRAM_ID_REGEXP = "^[A-Z]+$";

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<EventDto> createEvent(
            @Validated({Default.class, EventDto.OnCreate.class})
            @RequestBody EventDto dto) {
        log.info("POST /api/v1/events - eventId: {}", dto.getEventId());
        return ResponseEntity.status(HttpStatus.CREATED).body(eventService.createEvent(dto));
    }

    @GetMapping(value = "/{eventId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EventDto> getEvent(
            @PathVariable
            @Pattern(regexp = PROGRAM_ID_REGEXP, message = "Event ID must contain uppercase letters only (A-Z)")
            String eventId) {
        log.info("GET /api/v1/events/{}", eventId);
        return ResponseEntity.ok(eventService.getEvent(eventId));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EventDto>> getAllEvents() {
        log.info("GET /api/v1/events - retrieving all events");
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @PutMapping(
            value = "/{eventId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<EventDto> updateEvent(
            @PathVariable
            @Pattern(regexp = PROGRAM_ID_REGEXP, message = "Event ID must contain uppercase letters only (A-Z)")
            String eventId,
            @Valid @RequestBody EventDto dto) {
        log.info("PUT /api/v1/events/{}", eventId);
        return ResponseEntity.ok(eventService.updateEvent(eventId, dto));
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<Void> deleteEvent(
            @PathVariable
            @Pattern(regexp = PROGRAM_ID_REGEXP, message = "Event ID must contain uppercase letters only (A-Z)")
            String eventId) {
        log.info("DELETE /api/v1/events/{}", eventId);
        eventService.deleteEvent(eventId);
        return ResponseEntity.noContent().build();
    }
}
