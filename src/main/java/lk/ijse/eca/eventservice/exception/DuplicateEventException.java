package lk.ijse.eca.eventservice.exception;

public class DuplicateEventException extends RuntimeException {

    public DuplicateEventException(String eventId) {
        super("Event already exists with ID: " + eventId);
    }
}
